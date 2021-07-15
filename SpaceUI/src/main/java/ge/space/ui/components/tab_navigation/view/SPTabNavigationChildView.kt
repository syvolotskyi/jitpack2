package ge.space.ui.components.tab_navigation.view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.core.graphics.ColorUtils
import ge.space.extensions.setTextStyle
import ge.space.extensions.tintColor
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTabNavigationChildViewLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.tab_navigation.data.SPTabNavigationModel

/**
 * Child view extended from [FrameLayout] that allows to set view each Tab navigation item.
 */
class SPTabNavigationChildView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy {
        SpTabNavigationChildViewLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    /**
     * Set a new style
     */
    fun setStyle(newStyle: Int) {
        val styleAttrs =
            context.theme.obtainStyledAttributes(newStyle, R.styleable.sp_tab_navigation_child_view)
        styleAttrs.run {
            binding.tabTitle.setTextStyle(newStyle)
            setStyleResources()
        }
    }

    /**
     * Allows to update style resources
     */
    private fun TypedArray.setStyleResources() {
        activeColor = getColor(
            R.styleable.sp_tab_navigation_child_view_activeColor,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        inActiveColor = getColor(
            R.styleable.sp_tab_navigation_child_view_inActiveColor,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        defaultBackgroundColor = getColor(
            R.styleable.sp_tab_navigation_child_view_defaultBackgroundColor,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        text =
            getColor(R.styleable.sp_tab_navigation_child_view_text, SPBaseView.DEFAULT_OBTAIN_VAL)
        image =
            getColor(R.styleable.sp_tab_navigation_child_view_image, SPBaseView.DEFAULT_OBTAIN_VAL)

    }

    /**
     * Set a active color
     */
    var activeColor: Int = 0
        set(value) {
            field = value
            onActiveStatusChange()
        }

    /**
     * Set a inActive color
     */
    var inActiveColor: Int = 0
        set(value) {
            field = value
            onActiveStatusChange()
        }

    /**
     * Set a navigation item resource
     */
    var navigationItem: SPTabNavigationModel? = null
        set(value) {
            value?.let {
                binding.tabImage.setImageResource(value.image)
                binding.tabTitle.text = resources.getString(value.text)
            }
            field = value
        }

    /**
     * Set a active status
     */
    var isActive = false
        set(value) {
            field = value
            onActiveStatusChange()
        }

    /**
     * Sets a background color.
     */
    var defaultBackgroundColor: Int = 0
        set(value) {
            field = value
            onActiveStatusChange()
        }

    /**
     * Sets a navigation title.
     */
    var text: Int = 0
        set(value) {
            field = value
            onActiveStatusChange()
        }

    /**
     * Sets a navigation image.
     */
    var image: Int = 0
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
            setStyleResources()
            setTextAppearance()
            navigationItem = staticNavigationItem
            onActiveStatusChange()
        }
    }

    /**
     * Set a default (static) navigation item. This item is necessary to show visually in xml file
     */
    private var staticNavigationItem = SPTabNavigationModel(
        text = text,
        image = image
    )

    /**
     * change focus per status.
     * <p>
     * tintColor changing image background color per status.
     * tabImageContainer.isSelected changing background per status
     * tabImageContainer.color changing background per status
     * tabTitle.isEnabled sets texts color per status
     * <p>
     */
    private fun onActiveStatusChange() {
        with(binding) {
            tabImage.tintColor = if (isActive) activeColor else inActiveColor
            @ColorInt val alphaColor = ColorUtils.setAlphaComponent(activeColor, CARD_DEFAULT_ALPHA)
            tabImageContainer.color = if (isActive) alphaColor else defaultBackgroundColor
            tabImageContainer.isSelected = isActive
            tabTitle.isEnabled = isActive

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