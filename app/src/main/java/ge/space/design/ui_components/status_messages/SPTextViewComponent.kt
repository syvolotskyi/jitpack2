package ge.space.design.ui_components.status_messages

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.extensions.onTextChanged
import ge.space.ui.components.statusmessage.SPTextView

class SPTextViewComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.status_textview

    override fun getDescriptionResId(): Int = R.string.status_textview_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val textViews = mutableListOf<SPTextView>()

            SPTextViewStyles.list.onEach { textViewSample ->

                val itemBinding = SpItemTextViewShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                with(itemBinding.tvSuccess) {
                    setViewStyle(textViewSample.styleId)
                    text = itemBinding.tvSuccess.resources.getResourceEntryName(
                        textViewSample.styleId
                    )
                    textViews.add(this)
                }

                itemBinding.cbGravity1.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tvSuccess.viewGravity = if (isChecked) SPTextView.ViewGravity.START
                    else SPTextView.ViewGravity.CENTER
                }

                layoutBinding.textInput.onTextChanged { s ->
                    textViews.onEach { it.text = s }
                }

            }
            return layoutBinding.root

        }
    }
}