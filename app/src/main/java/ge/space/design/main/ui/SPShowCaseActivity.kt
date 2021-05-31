package ge.space.design.main.ui

import android.app.Dialog
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
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
import ge.space.ui.view.dialog.SPDialogInfoHolder
import ge.space.ui.view.dialog.SPInfoDialog
import ge.space.ui.view.dialog.view.SPDialogBottomVerticalButton


class SPShowCaseActivity : SPBaseActivity(), SPShowCaseDisplay {

    private lateinit var binding: SpActivitySimpleShowcaseBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = SpActivitySimpleShowcaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /*
            Android Studio launch flag: -e "component_name" "yourComponent"
        */
        componentSP = (if (intent.hasExtra(EXTRA_COMPONENT_NAME)) {
            try {
                val componentName = requireNotNull(intent.getStringExtra(EXTRA_COMPONENT_NAME))
                Class.forName(componentName).newInstance() as? SPShowCaseComponent
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        } else {
            intent?.getSerializableExtra(EXTRA_SHOWCASE_COMPONENT) as? SPShowCaseComponent
        }) ?: DesignSystemComponents

        setUpToolbar()

        if (componentSP.hasSubComponents) {
            bindComponentsList(componentSP.flattenSubComponentSPS)
        } else {
            val componentClass = componentSP.getComponentClass()
            if (componentClass == null) {
                finishWithError("Component not found")
                return
            }
            try {
                SPComponentLauncher.launch(componentClass, this, SPShowCaseEnvironment(this))
            } catch (e: Exception) {
                e.printStackTrace()
                finishWithError("Can't show component: ${e.message}")
            }
        }


        SPInfoDialog.SPInfoDialogBuilder(this)
                .setTitle("Title")
                .setLabel("Label")
                .setButtons(
                        multiple = true,
                        buttons = arrayOf(
                                SPDialogInfoHolder("Label 1",SPDialogBottomVerticalButton.BottomButtonType.Default) {
                                    Toast.makeText(this, "hello from label 1", Toast.LENGTH_SHORT).show()
                                },
                                SPDialogInfoHolder("Label 2",SPDialogBottomVerticalButton.BottomButtonType.Default) {
                                    Toast.makeText(this, "hello from label 2", Toast.LENGTH_SHORT).show()
                                }
                        )
                )
                .build()
                .onButtonsClick { label ->
                    println(label)
                }
                .show(supportFragmentManager, SPInfoDialog::class.java.name)



    }


    private fun setUpToolbar() {
        setSupportActionBar(binding.toolbarView)

        supportActionBar?.setTitle(componentSP.getNameResId())
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.toolbarView.navigationIcon?.setColorFilter(
            resources.getColor(R.color.appPrimaryColor),
            PorterDuff.Mode.SRC_ATOP
        );


        if (componentSP.getDescriptionResId() != NO_RES_ID) {
            binding.descriptionText.text = getString(
                componentSP.getDescriptionResId(), *componentSP.getDescriptionFormatArgs()
            )
        } else {
            binding.descriptionText.visibility = View.GONE
        }
    }

    private fun bindComponentsList(componentSPS: List<SPShowCaseComponent>) {
        val listBinding = SpLayoutSimpleShowcaseListBinding.inflate(
            layoutInflater, binding.contentView, true
        )

        this.componentSPS = componentSPS
        this.componentsListBinding = listBinding

        listBinding.recyclerView.layoutManager = LinearLayoutManager(this)
        listBinding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )

        val adapter = SimpleListAdapter<SpItemComponentBinding, SPShowCaseComponent>(
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
                        text = "getString(R.string.no_description)"
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


    override fun show(SPLaunchAction: SPLaunchAction) {
        val actionBinding = SpLayoutSimpleShowcaseActionBinding.inflate(
            layoutInflater, binding.contentView, true
        )
        actionBinding.showButton.setOnClickListener { SPLaunchAction.launch() }
    }
}