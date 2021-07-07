package ge.space.ui.components.tab_navigation.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.ColorUtils
import ge.space.extensions.getColorRes
import ge.space.extensions.setTextStyle
import ge.space.extensions.tintColor
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTabNavigationChildViewLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.tab_navigation.data.SPTabNavigationModel


open class SPTabNavigationChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        SpTabNavigationChildViewLayoutBinding.inflate(LayoutInflater.from(context), this)
    }
    /**
     * Set a active color
     */
    private var activeColor: Int = context.getColorRes(R.color.appPrimaryColor)
    /**
     * Set a inActive color
     */
    private var inActiveColor: Int = context.getColorRes(R.color.light_label_secondary)
    /**
     * Set a default navigation background Color
     */
    private var defaultBackgroundColor: Int = context.getColorRes(R.color.white)

    /**
     * Set a default (static) navigation item
     */
    private var staticNavigationItem = SPTabNavigationModel(
        title = R.string.component_tab_navigation_by_number,
        image = R.drawable.ic_bank_24_regular
    )

    /**
     * Set a navigation item resource
     */
    var navigationItem: SPTabNavigationModel? = null
        set(value) {
            value?.let {
                binding.tabImage.setImageResource(value.image)
                binding.tabTitle.text = resources.getString(value.title)
            }
            field = value
        }

    /**
     * Set a active navigation item
     */
    var isActive = false
        set(value) {
            field = value
            onActiveStatusChange()
        }

    init {

        context.withStyledAttributes(
            attrs,
            R.styleable.sp_tab_navigation_child_view,
            defStyleAttr
        ) {
            isActive = getBoolean(R.styleable.sp_tab_navigation_child_view_isActive, false)
            setTextAppearance()
            navigationItem = staticNavigationItem
            onActiveStatusChange()
        }
    }

    /**
     * change active status per isActive status
     */
    private fun onActiveStatusChange() {
        with(binding) {
            tabImage.tintColor = if (isActive) activeColor else inActiveColor
            @ColorInt val alphaColor = ColorUtils.setAlphaComponent(activeColor, CARD_DEFAULT_ALPHA)
            tabImageContainer.color = if (isActive) alphaColor else defaultBackgroundColor
            tabTitle.isSelected = isActive
        }
    }

    /**
     * set textAppearance title View
     */
    private fun TypedArray.setTextAppearance() {
        binding.tabTitle.setTextStyle(
            getResourceId(
                R.styleable.sp_tab_navigation_child_view_childTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
        )
    }

    /**
     * Set a alpha navigation view
     */
    companion object {
        const val CARD_DEFAULT_ALPHA = 25

    }
}