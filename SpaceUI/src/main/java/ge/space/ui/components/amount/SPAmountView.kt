package ge.space.ui.components.amount

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.ViewSwitcher
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpAmountLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.handleAttributeAction
import ge.space.ui.util.extension.setTextStyle
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

class SPAmountView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPAmountView_Brand
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpAmountLayoutBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )
    }

    /**
     * Sets a component title.
     */
    var titleText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleTV.text = titleText
        }

    /**
     * Sets a description title.
     */
    var descText: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.descriptionText.text = value
        }

    /**
     * Sets a add-on text.
     */
    private var addOnText: String = EMPTY_TEXT

    /**
     * Sets a add-on type.
     */
    private var addOnType: AddOnType = AddOnType.None

    /**
     * Sets a component title.
     */
    var amount: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.amountTV.text = amount
        }

    /**
     * Sets a component title.
     */
    var currency: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.currencyTV.text = currency
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a description text appearance
     */
    @StyleRes
    private var descriptionTextAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a amount text appearance
     */
    @StyleRes
    private var amountTextAppearance: Int = SPBaseView.DEFAULT_INT

    /**
     * Sets a amount text appearance
     */
    @StyleRes
    private var currencyTextAppearance: Int = SPBaseView.DEFAULT_INT

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPAmountView,
            defStyleAttr,
            defStyleRes
        ) {
            applyAmountStyledAttrs()
        }
    }

    private fun TypedArray.applyAmountStyledAttrs() {

        getResourceId(R.styleable.SPAmountView_titleTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { textAppearance = it }

        getResourceId(
            R.styleable.SPAmountView_descriptionTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { descriptionTextAppearance = it }

        getResourceId(R.styleable.SPAmountView_amountTextAppearance, SPBaseView.DEFAULT_OBTAIN_VAL)
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { amountTextAppearance = it }

        getResourceId(
            R.styleable.SPAmountView_currencyTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { currencyTextAppearance = it }

        getString(R.styleable.SPAmountView_title).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { titleText = it }

        getString(R.styleable.SPAmountView_descriptionText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { descText = it }

        getString(R.styleable.SPAmountView_amount).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { amount = it }

        getString(R.styleable.SPAmountView_currency).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { currency = it }

        getString(R.styleable.SPAmountView_addOnText).orEmpty()
            .handleAttributeAction(EMPTY_TEXT) { addOnText = it }

        val addOnInd = getInt(
            R.styleable.SPAmountView_addOn,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        addOnType = AddOnType.values()[addOnInd]

        updateTextAppearances()
        handleAddOnView()
    }

    private fun handleAddOnView() {
        when (addOnType) {
            AddOnType.Info -> SPViewData.SPInfoTextData(addOnText)
            AddOnType.Tooltip -> SPViewData.SPInfoTextData(addOnText)
            else -> null
        }?.createView(context)?.let { binding.addOnFL.addView(it) }

    }

    /**
     * Sets title, description, amount and currency text appearance
     */
    private fun updateTextAppearances() {
        binding.titleTV.setTextStyle(textAppearance)
        binding.descriptionText.setTextStyle(descriptionTextAppearance)
        binding.amountTV.setTextStyle(amountTextAppearance)
        binding.currencyTV.setTextStyle(currencyTextAppearance)
    }

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPAmountView
        ) {
            applyAmountStyledAttrs()
        }
    }

    enum class AddOnType {
        None, Info, Tooltip
    }
}