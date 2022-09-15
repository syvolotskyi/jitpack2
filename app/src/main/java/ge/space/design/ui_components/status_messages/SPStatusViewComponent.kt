package ge.space.design.ui_components.status_messages

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.text_view.SPTextView
import ge.space.ui.util.extension.onTextChanged

class SPStatusViewComponent : ShowCaseComponent {
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

                layoutBinding.textInput.onTextChanged { s ->
                    textViews.onEach { it.text = s }
                }

            }
            return layoutBinding.root

        }
    }
}