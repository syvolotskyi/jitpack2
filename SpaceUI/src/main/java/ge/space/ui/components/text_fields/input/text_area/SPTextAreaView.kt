package ge.space.ui.components.text_fields.input.text_area

import android.content.Context
import android.text.InputType
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan
import android.util.AttributeSet
import android.view.Gravity
import android.widget.*
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.onChange
import ge.space.extensions.onClick
import ge.space.spaceui.R
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.base.SPTextInputViewType
import ge.space.ui.util.extension.showKeyboard
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView


/**
 * Field view extended from [SPTextFieldInput] to add the possibility to create
 * a large input text with a symbol counter.
 *
 */
class SPTextAreaView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Area,
) : SPTextFieldInput(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * A text view contains information about current text length and max length.
     */
    private var counterView: TextView? = null

    override fun handleContentInputView() {
        binding.flInputFieldContainer.removeAllViews()
        binding.flInputFieldContainer.addView(createScrollView())
        setupCounterView()
        setupFocusChangeListener()
        binding.flInputFieldContainer.addView(counterView)
        registerCounterListener()
    }

    override fun setTextLength(value: Int) {
        super.setTextLength(value)
        updateCounterText()
    }

    private fun registerCounterListener() {
        (contentInputView as EditText).onChange {
            updateCounterText()
        }
    }

    private fun setupCounterView() {
        counterView = (SPViewData.SPTextData(
            params = SPViewData.SPViewDataParams(
                gravity = Gravity.END or Gravity.CENTER_VERTICAL,
                paddingBottom = resources.getDimensionPixelSize(R.dimen.dimen_p_4),
            )
        ).createView(context) as TextView).apply {
            setText(
                getCounterText(),
                TextView.BufferType.SPANNABLE
            )
        }
    }

    private fun updateCounterText() =
        counterView?.setText(
            getCounterText(),
            TextView.BufferType.SPANNABLE
        )

    private fun createScrollView() = ScrollView(context).apply {
        // Configure scrollview
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1.0f)
        setFadingEdgeLength(resources.getDimensionPixelSize(R.dimen.dimen_p_60))
        isVerticalFadingEdgeEnabled = true
        isVerticalScrollBarEnabled = false
        overScrollMode = OVER_SCROLL_NEVER

        // Add editText
        addView(LinearLayout(context).apply {
            addView(contentInputView)
            isFillViewport = true
            onClick { contentInputView.showKeyboard() }
        })
    }

    private fun getCounterText(): SpannableString {
        val spannable = SpannableString("${text.length} / $maxLength")

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
        SPTextInputViewType.SPEditTextViewType(
            hint = hint, lines = null,
            inputType = InputType.TYPE_CLASS_TEXT
                    or InputType.TYPE_TEXT_FLAG_MULTI_LINE
                    or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD,
            params = SPViewData.SPViewDataParams(
                gravity = Gravity.START or Gravity.TOP,
                paddingTop = resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                paddingBottom = resources.getDimensionPixelSize(R.dimen.dimen_p_12),
            )
        )

}