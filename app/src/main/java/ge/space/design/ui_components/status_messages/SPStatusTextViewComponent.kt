package ge.space.design.ui_components.status_messages

import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.LinearLayout
import androidx.core.view.setMargins
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemStatusTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.extensions.dpToPx
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
                        val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
                        params.gravity = if (isChecked) Gravity.START
                        else Gravity.CENTER
                        val margins = context.dpToPx(12.toFloat())
                        params.setMargins(margins)
                        layoutParams = params
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