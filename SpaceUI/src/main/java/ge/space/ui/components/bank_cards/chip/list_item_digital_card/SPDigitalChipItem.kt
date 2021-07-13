package ge.space.ui.components.bank_cards.chip.list_item_digital_card

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.core.content.ContextCompat
import androidx.core.content.withStyledAttributes
import ge.space.extensions.onClick
import ge.space.extensions.setTextStyle
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDigitalChipItemLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPBankCardGradient
import ge.space.ui.util.extension.visibleOrGone
import ge.space.ui.util.extension.visibleOrInvisible

/**
 * A chip item view which is used inside a list and can be selectable
 *
 * @property text allows to set a string for a text label
 * @property currency sets a symbol of a currency
 * @property chipBackground applies a specific chip background
 * @property itemEnabled sets both an opacity with enabled status
 */
class SPDigitalChipItem @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Sets a title for the item
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.tvTitle.text = value
        }

    /**
     * Sets a currency symbol for the item
     */
    var currency: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.tvCurrency.text = value
        }

    /**
     * Applies a chip view background
     */
    var chipBackground: SPBankCardGradient = SPBankCardGradient.SPLinear()
        set(value) {
            field = value

            binding.chip.cardBackground = value
        }

    /**
     * Sets opacity and non-clickable to whole view if the property is false
     */
    var itemEnabled: Boolean = true
        set(value) {
            field = value

            handleItemEnabled()
        }

    /**
     * Binds a view
     */
    private val binding =
        SpDigitalChipItemLayoutBinding.inflate(LayoutInflater.from(context), this)

    /**
     * Action for selection
     */
    private var onSelect: ((Boolean) -> Unit)? = null

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_base_view,
            defStyleAttr
        ) {
            with(binding) {
                tvTitle.setTextStyle(
                    getResourceId(
                        R.styleable.sp_selectable_chip_item_selectableChipItemTitleStyle,
                        R.style.h700_medium_caps_title
                    )
                )

                val currencyStyleRes = getResourceId(
                    R.styleable.sp_selectable_chip_item_selectableChipItemCurrencyStyle,
                    R.style.h600_medium_currency
                )
                tvCurrency.setTextStyle(currencyStyleRes)

                context.theme.obtainStyledAttributes(
                    currencyStyleRes,
                    R.styleable.sp_selectable_chip_item_currency
                ).run {
                    val drawableResId = getResourceId(
                        R.styleable.sp_selectable_chip_item_currency_android_background,
                        R.drawable.bg_currency
                    )

                    tvCurrency.background = ContextCompat.getDrawable(context, drawableResId)
                }
            }
        }

        setCheckCallback()
    }

    /**
     * Sets [onSelect] action
     */
    fun setOnSelect(onItemSelect: (Boolean) -> Unit) {
        onSelect = onItemSelect
    }

    /**
     * Toggles the check box
     */
    fun toggle() {
        binding.checkButton.toggle()
    }

    private fun setCheckCallback() {
        with(binding) {
            onClick {
                checkButton.toggle()
                onSelect?.invoke(checkButton.isChecked)
            }

            checkButton.onClick {
                onSelect?.invoke(checkButton.isChecked)
            }
        }
    }

    private fun handleItemEnabled() {
        with(binding) {
            root.isEnabled = itemEnabled
            checkButton.visibleOrInvisible(itemEnabled)
            ivCheck.visibleOrGone(!itemEnabled)
            root.alpha = getEnabledAlpha()
        }
    }

    private fun getEnabledAlpha() = if (itemEnabled) NON_OPACITY
        else DISABLED_OPACITY

    companion object {
        private const val NON_OPACITY = 1f
        private const val DISABLED_OPACITY = .32f
    }
}