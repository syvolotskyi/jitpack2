package ge.space.design.main.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.Menu
import android.view.MenuItem
import android.widget.Filter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutSimpleShowcaseListBinding
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.flattenSubComponentSPS
import ge.space.design.main.util.hasSubComponents
import ge.space.design.showThemeDialog

abstract class SPBaseActivity : AppCompatActivity() {

    protected lateinit var componentSP: SPShowCaseComponent
    protected lateinit var componentSPS: List<SPShowCaseComponent>
    protected lateinit var componentsListBinding: SpLayoutSimpleShowcaseListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setProperTheme()
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
        if (componentSP.hasSubComponents) {
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

    // filtering
    private val componentsFilter: Filter by lazy {
        object : Filter() {
            // lookup collection
            val componentNames by lazy {
                componentSPS.flatMap { it.flattenSubComponentSPS }
                    .map { getString(it.getNameResId()) to it }
            }

            override fun performFiltering(constraint: CharSequence?)
                    : FilterResults {
                val result = FilterResults()
                if (constraint.isNullOrBlank()) {
                    result.values = componentSPS
                    result.count = componentSPS.count()
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
                val filteredItems = results.values as List<SPShowCaseComponent>
                (componentsListBinding.recyclerView.adapter as? SimpleListAdapter<*, SPShowCaseComponent>)
                    ?.setItems(filteredItems)
            }
        }
    }

    protected fun finishWithError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        finish()
    }

    val preferesManager by lazy {
        PreferenceManager.getDefaultSharedPreferences(this)
    }

    companion object {

        const val EXTRA_COMPONENT_NAME = "component_name"
        const val EXTRA_SHOWCASE_COMPONENT = "showcase_component"

        const val PREFERENCES_THEME = "PREFERENCES_THEME"

        fun start(
            context: Context,
            componentSP: SPShowCaseComponent
        ) {
            val intent = Intent(context, SPShowCaseActivity::class.java)
                .apply { putExtra(EXTRA_SHOWCASE_COMPONENT, componentSP) }
            context.startActivity(intent)
        }
    }
}