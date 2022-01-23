package ge.space.ui.components.text_fields.input.text_area

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.base.SPTextInputViewType
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPTextAreaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Area,
) : SPTextFieldInput(context, attrs, defStyleAttr, defStyleRes) {

    private var counterView: TextView? = null

    override fun handleContentInputView() {
        super.handleContentInputView()

        binding.flInputFieldContainer.removeView(counterView)
        counterView =
            (SPViewData.SPTextData(params = SPViewData.SPViewDataParams(gravity = Gravity.END or Gravity.CENTER_VERTICAL))
                .createView(context) as TextView).apply {
                setText(
                    getCounterText(),
                    TextView.BufferType.SPANNABLE
                )
            }
        binding.flInputFieldContainer.addView(counterView)
    }

    private fun getCounterText(): SpannableString {
        val spannable = SpannableString("${text.length} / $maxLenght")

        spannable.setSpan(
            TextAppearanceSpan(context, R.style.h800_bold_label_primary),
            0,
            text.length.toString().length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        spannable.setSpan(
            TextAppearanceSpan(context, R.style.h800_bold_label_secondary),
            text.length.toString().length,
            spannable.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return spannable
    }


    override fun getContentEditText(): SPTextInputViewType.SPEditTextViewType =
        SPTextInputViewType.SPEditTextViewType(lines = Int.MAX_VALUE)

}