package ge.space.ui.components.tab_navigation.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes
import androidx.core.graphics.ColorUtils
import androidx.core.widget.TextViewCompat
import ge.space.extensions.getColorRes
import ge.space.extensions.tintColor
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTabNavigationChildViewLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.tab_navigation.data.SPTabNavigationModel


open class SPTabNavigationChildView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {
    private val binding by lazy { SpTabNavigationChildViewLayoutBinding.inflate(LayoutInflater.from(context),this) }

    private var activeColor: Int = context.getColorRes(R.color.appPrimaryColor)
    private var inActiveColor: Int = context.getColorRes(R.color.light_label_secondary)
    private var defaultBackgroundColor: Int = context.getColorRes(R.color.white)


    var staticNavigationItem = SPTabNavigationModel (title = R.string.component_tab_navigation_by_number,
        image = R.drawable.ic_bank_24_regular)

    var navigationItem: SPTabNavigationModel? = null
        set(value) {
            value?.let {
                binding.tabImage.setImageResource(value.image)
                binding.tabTitle.text = resources.getString(value.title)
            }
            field = value
        }

    var isActive = false
        set(value) {
            field = value
            onActiveStatusChange()
        }


    override fun getTag(): Any {
        return binding.childViewContainer.tag
    }

    override fun setTag(tag: Any?) {
        binding.childViewContainer.tag = tag
    }

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.sp_tab_navigation_child_view, 0, 0)

            val textAppearance = typedArray.getResourceId(
                R.styleable.sp_tab_navigation_child_view_childTextAppearance,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            updateTextAppearance(textAppearance = textAppearance)

            isActive = typedArray.getBoolean(R.styleable.sp_tab_navigation_child_view_isActive, false)
            typedArray.recycle()
        }
        navigationItem = staticNavigationItem
        onActiveStatusChange()
    }

     private fun onActiveStatusChange() {

        with(binding) {
            tabImage.tintColor = if (isActive) activeColor else inActiveColor
            tabTitle.isSelected = isActive
            @ColorInt val alphaColor = ColorUtils.setAlphaComponent(activeColor, 25)
            tabImageContainer.color = if(isActive) alphaColor else defaultBackgroundColor
        }
    }

    private fun updateTextAppearance(@StyleRes textAppearance: Int) {
        with(binding){
            TextViewCompat.setTextAppearance(tabTitle, textAppearance)

        }
    }

}