package ge.space.design.ui_components.status_messages

import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.widget.CompoundButton
import android.widget.LinearLayout
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.core.view.setMargins
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemStatusTextViewShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonsShowcaseBinding
import com.google.android.material.textview.MaterialTextView
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.text_fields.SPTextFieldsComponent
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
            val textViews = mutableListOf<MaterialTextView>()

            SPStatusTextViewStyles.list.onEach { textViewSample ->

                val itemBinding = SpItemStatusTextViewShowcaseBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.buttonsLayout,
                    true
                )

                itemBinding.tvSuccess.setViewStyle(R.style.SPStatusTextView_Success)
                itemBinding.tvSuccess.updateTextAppearance(R.style.h800_medium_accent_magenta)
                textViews.add(itemBinding.tvSuccess)

                itemBinding.tvSuccess.setViewStyle(R.style.SPStatusTextView_Error)
                itemBinding.tvSuccess.updateTextAppearance(R.style.h800_medium_accent_magenta)
                textViews.add(itemBinding.tvSuccess)


                textViews.add(itemBinding.tvSuccess)
                itemBinding.tvSuccess.setViewStyle(R.style.SPStatusTextView_Pending)
                itemBinding.tvSuccess.updateTextAppearance(R.style.h800_medium_accent_orange)

                textViews.add(itemBinding.tvSuccess)
                itemBinding.tvSuccess.setViewStyle(R.style.SPStatusTextView_Info)
                itemBinding.tvSuccess.updateTextAppearance(R.style.h800_medium_brand_primary)

                fun MaterialTextView.toggleGravity(isChecked: Boolean) {
                    val params = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT)
                    params.gravity = if (isChecked) Gravity.START
                    else Gravity.CENTER
                    val margins = context.dpToPx(12.toFloat())
                    params.setMargins(margins)
                    layoutParams = params
                }

                itemBinding.cbGravity1.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tvSuccess.toggleGravity(isChecked)
                }

                /*itemBinding.cbGravity2.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tvError.toggleGravity(isChecked)
                }

                itemBinding.cbGravity3.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tvPending.toggleGravity(isChecked)
                }

                itemBinding.cbGravity4.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.tvInfo.toggleGravity(isChecked)
                }*/

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