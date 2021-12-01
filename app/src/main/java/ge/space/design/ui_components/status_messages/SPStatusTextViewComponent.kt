package ge.space.design.ui_components.status_messages

import android.text.Editable
import android.text.TextWatcher
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemStatusTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.statusmessage.SPMessageStatus
import ge.space.ui.components.statusmessage.SPStatusTextView

class SPStatusTextViewComponent : ShowCaseComponent {
    override fun getNameResId(): Int = R.string.status_textview

    override fun getDescriptionResId(): Int = R.string.status_textview_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonsShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            val textViews = mutableListOf<SPStatusTextView>()

            SPStatusTextViewStyles.list.onEach { textViewSample ->

                val styleId = textViewSample.styleId

                val itemBinding = SpItemStatusTextViewShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                itemBinding.tvStatus.status = SPMessageStatus.PENDING
                textViews.add(itemBinding.tvStatus)

                itemBinding.tvStatus.status = SPMessageStatus.SUCCESS
                textViews.add(itemBinding.tvStatus)

                itemBinding.tvStatus.status = SPMessageStatus.ERROR
                textViews.add(itemBinding.tvStatus)

                itemBinding.tvStatus.setViewStyle(textViewSample.styleId)

                itemBinding.cbGravity.setOnCheckedChangeListener { _, isChecked ->
                    with(itemBinding.tvStatus) {
                        gravity = if (isChecked) SPStatusTextView.Gravity.Start
                        else SPStatusTextView.Gravity.Center
                    }
                }

                layoutBinding.textInput.addTextChangedListener(object : TextWatcher {

                    override fun afterTextChanged(s: Editable?) {
                    }

                    override fun beforeTextChanged(
                        s: CharSequence?,
                        start: Int,
                        count: Int,
                        after: Int
                    ) {
                    }

                    override fun onTextChanged(
                        s: CharSequence?,
                        start: Int,
                        before: Int,
                        count: Int
                    ) {
                        textViews.onEach { it.text = s.toString() }
                    }
                })

            }
            return layoutBinding.root

        }
    }
}