package ge.space.design.main

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Filter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpActivitySimpleShowcaseBinding
import com.example.spacedesignsystem.databinding.SpItemComponentBinding
import com.example.spacedesignsystem.databinding.SpLayoutSimpleShowcaseActionBinding
import com.example.spacedesignsystem.databinding.SpLayoutSimpleShowcaseListBinding
import ge.space.design.DesignSystemComponents
import ge.space.design.main.*
import ge.space.design.showThemeDialog


class SimpleShowCaseActivity : AppCompatActivity(), ShowCaseDisplay {

    val preferesManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    companion object {

        private const val EXTRA_COMPONENT_NAME = "component_name"
        private const val EXTRA_SHOWCASE_COMPONENT = "showcase_component"

        const val PREFERENCES_THEME = "PREFERENCES_THEME"

        fun start(
            context: Context,
            component: ShowCaseComponent
        ) {
            val intent = Intent(context, SimpleShowCaseActivity::class.java)
                .apply { putExtra(EXTRA_SHOWCASE_COMPONENT, component) }
            context.startActivity(intent)
        }
    }

    private lateinit var component: ShowCaseComponent
    private lateinit var components: List<ShowCaseComponent>
    private lateinit var componentsListBinding: SpLayoutSimpleShowcaseListBinding

    // filtering
    private val componentsFilter: Filter by lazy {
        object : Filter() {

            // lookup collection
            val componentNames by lazy {
                components.flatMap { it.flattenSubComponents }
                    .map { getString(it.getNameResId()) to it }
            }

            override fun performFiltering(constraint: CharSequence?)
                    : FilterResults {
                val result = FilterResults()
                if (constraint.isNullOrBlank()) {
                    result.values = components
                    result.count = components.count()
                } else {
                    val filteredItems = componentNames
                        .filter { (name) -> name.contains(constraint.trim(), ignoreCase = true) }
                        .map { (_, component) -> component }
                        .toList()
                    result.values = filteredItems
                    result.count = filteredItems.count()
                }
                return result
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(
                constraint: CharSequence?,
                results: FilterResults
            ) {
                val filteredItems = results.values as List<ShowCaseComponent>
                (componentsListBinding.recyclerView.adapter as? SimpleListAdapter<*, ShowCaseComponent>)
                    ?.setItems(filteredItems)
            }
        }
    }

    private lateinit var binding: SpActivitySimpleShowcaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setProperTheme()
        super.onCreate(savedInstanceState)

        binding = SpActivitySimpleShowcaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            Android Studio launch flag: -e "component_name" "yourComponent"
        */
        component = (if (intent.hasExtra(EXTRA_COMPONENT_NAME)) {
            try {
                val componentName = requireNotNull(intent.getStringExtra(EXTRA_COMPONENT_NAME))
                Class.forName(componentName).newInstance() as? ShowCaseComponent
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            intent?.getSerializableExtra(EXTRA_SHOWCASE_COMPONENT) as? ShowCaseComponent
        }) ?: DesignSystemComponents

        setUpToolbar()

        if (component.hasSubComponents) {
            bindComponentsList(component.flattenSubComponents)
        } else {
            val componentClass = component.getComponentClass()
            if (componentClass == null) {
                finishWithError("Component not found")
                return
            }
            try {
                ComponentLauncher.launch(componentClass, this, ShowCaseEnvironment(this))
            } catch (e: Exception) {
                e.printStackTrace()
                finishWithError("Can't show component: ${e.message}")
            }
        }
    }

    private fun setProperTheme() {
        val theme = when (preferesManager.getInt(PREFERENCES_THEME, 0)) {
            0 -> R.style.AppThemeDark
            1 -> R.style.AppThemeWhite
            else -> null
        }
        theme?.let { setTheme(it) }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        if (component.hasSubComponents) {
            menuInflater.inflate(R.menu.sp_showcase_menu, menu)
            val searchView = menu.findItem(R.id.action_search).actionView as? SearchView
            searchView?.apply {
                maxWidth = Int.MAX_VALUE
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        componentsFilter.filter(newText)
                        return true
                    }
                })
            }
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.action_theme -> {
                showThemeDialog()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbarView)

        supportActionBar?.setTitle(component.getNameResId())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarView.navigationIcon?.setColorFilter(resources.getColor(R.color.appPrimaryColor), PorterDuff.Mode.SRC_ATOP);


        if (component.getDescriptionResId() != NO_RES_ID) {
            binding.descriptionText.text = getString(
                component.getDescriptionResId(), *component.getDescriptionFormatArgs()
            )
        } else {
            binding.descriptionText.visibility = View.GONE
        }
    }

    private fun bindComponentsList(components: List<ShowCaseComponent>) {
        val listBinding = SpLayoutSimpleShowcaseListBinding.inflate(
            layoutInflater, binding.contentView, true
        )

        this.components = components
        this.componentsListBinding = listBinding

        listBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        listBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        val adapter = SimpleListAdapter<SpItemComponentBinding, ShowCaseComponent>(
            components
        ).setup {
            onCreate { parent ->
                SpItemComponentBinding.inflate(layoutInflater, parent, false)
            }
            onBind { binding, component, _ ->
                binding.title.setText(component.getNameResId())
                if (component.isNew) {
                    binding.title.apply { append(" "); append(getNewBadge()) }
                }
                with(binding.description) {
                    if (component.getDescriptionResId() != NO_RES_ID) {
                        text = getString(
                            component.getDescriptionResId(), *component.getDescriptionFormatArgs()
                        )
                        setTypeface(null, Typeface.NORMAL)
                    } else {
                        text = "getString(R.string.no_description)"
                        setTypeface(null, Typeface.ITALIC)
                    }
                }
            }
            onClick { _, item, _ ->
                start(this@SimpleShowCaseActivity, item)
            }
        }
        listBinding.recyclerView.adapter = adapter
    }
    override fun show(intent: Intent) {
        val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
            layoutInflater, binding.contentView, true
        )
        actionBinding.showButton.setOnClickListener { startActivity(intent) }
    }

    override fun show(fragment: Fragment) {
        if (fragment is DialogFragment) {
            val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
                layoutInflater, binding.contentView, true
            )
            actionBinding.showButton.setOnClickListener {
                fragment.show(supportFragmentManager, fragment::class.java.simpleName)
            }
        } else {
            supportFragmentManager.beginTransaction()
                .replace(binding.contentView.id, fragment)
                .commit()
        }
    }

    override fun show(dialog: Dialog) {
        val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
            layoutInflater, binding.contentView, true
        )
        actionBinding.showButton.setOnClickListener { dialog.show() }
    }

    override fun show(view: View) {
        binding.contentView.addView(view)
    }


      override fun show(launchAction: LaunchAction) {
          val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
              layoutInflater, binding.contentView, true
          )
          actionBinding.showButton.setOnClickListener { launchAction.launch() }
      }

    private fun finishWithError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }
}