package ge.space.design.main.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
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
import ge.space.design.main.util.*
import ge.space.ui.util.extension.*

class SPShowCaseActivity : SPBaseActivity(), SPShowCaseDisplay {

    private lateinit var binding: SpActivitySimpleShowcaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SpActivitySimpleShowcaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()

        checkComponent()

        setUpToolbar()

    }

    /**
     *  Android Studio launch flag: -e "component_name" "yourComponent"
     */
    private fun initBundle() {
        showCaseComponent = (if (intent.hasExtra(EXTRA_COMPONENT_NAME)) {
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
    }

    private fun checkComponent() {
        if (showCaseComponent.hasSubComponents) {
            bindComponentsList(showCaseComponent.flattenSubComponentSPS)
        } else {
            val componentClass = showCaseComponent.getComponentClass()
            if (componentClass == null) {
                finishWithError(COMPONENT_NOT_FOUND)
                return
            }
            try {
                SPComponentLauncher.launch(componentClass, this, SPShowCaseEnvironment(this))
            } catch (e: Exception) {
                e.printStackTrace()
                finishWithError("$COMPONENT_CAN_NOT_SHOW ${e.message}")
            }
        }
    }

    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbarView)

        supportActionBar?.setTitle(showCaseComponent.getNameResId())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarView.navigationIcon?.setColorFilter(
            getColorFromAttribute(ge.space.spaceui.R.attr.brand_primary),
            PorterDuff.Mode.SRC_ATOP
        );


        if (showCaseComponent.getDescriptionResId() != NO_RES_ID) {
            binding.descriptionText.text = getString(
                showCaseComponent.getDescriptionResId(), *showCaseComponent.getDescriptionFormatArgs()
            )
        } else {
            binding.descriptionText.visibility = View.GONE
        }
    }

    private fun bindComponentsList(componentSPS: List<ShowCaseComponent>) {
        val listBinding = SpLayoutSimpleShowcaseListBinding.inflate(
            layoutInflater, binding.contentView, true
        )

        this.showCaseComponentList = componentSPS
        this.componentsListBinding = listBinding

        listBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        listBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        val adapter = SimpleListAdapter<SpItemComponentBinding, ShowCaseComponent>(
            componentSPS
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
                        text = NO_DESCRIPTIONS
                        setTypeface(null, Typeface.ITALIC)
                    }
                }
            }
            onClick { _, item, _ ->
                start(this@SPShowCaseActivity, item)
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


    override fun show(launchAction: SPLaunchAction) {
        val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
            layoutInflater, binding.contentView, true
        )
        actionBinding.showButton.setOnClickListener { launchAction.launch() }
    }

    companion object {
        const val COMPONENT_NOT_FOUND    = "Component not found"
        const val COMPONENT_CAN_NOT_SHOW = "Can't show component:"
        const val NO_DESCRIPTIONS        = "No Descriptions"
    }
}