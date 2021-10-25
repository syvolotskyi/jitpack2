package ge.space.design.ui_components.buttons.inline

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemInlineButtonsShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.components.buttons.SPButtonInline

class SPInlineButtonComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.inline_buttons

    override fun getDescriptionResId(): Int = R.string.inline_button_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val buttons = mutableListOf<SPButtonInline>()

            SPInlineButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId

                val itemBinding = SpItemInlineButtonsShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(".", resName)
                }

                itemBinding.button.src = buttonSample.src

                buttons.add(itemBinding.button)

                itemBinding.button.setOnClickListener {
                    Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
                }

                itemBinding.wrapContentCheck.setOnCheckedChangeListener { _, isChecked ->
                    with(itemBinding.button) {
                        buttonGravity = if (isChecked) {
                            SPButtonInline.ButtonGravity.Center
                        } else {
                            SPButtonInline.ButtonGravity.Left
                        }
                    }
                }

                itemBinding.disableCheck.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.button.isEnabled = !isChecked
                }

                itemBinding.captionCheck.setOnCheckedChangeListener { _, isChecked ->
                    with(itemBinding.button) {
                    description = if (isChecked) context.getString(R.string.inline_button_description) else EMPTY_TEXT
                }}


                itemBinding.button.setViewStyle(buttonSample.resId)
            }
            return layoutBinding.root

        }
    }
}