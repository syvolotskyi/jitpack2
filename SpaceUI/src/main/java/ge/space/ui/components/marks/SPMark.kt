package ge.space.ui.components.marks

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.setSize
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

/**
 * Mark view extended from [SPBaseView].
 *
 * @property hasBorder [Boolean] value which applies a border.
 * @property textAppearance [Int] value sets text Appearance for initials marks
 */
class SPMark @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPMark_Size40
) : SPBaseView(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {


    /**
     * Allows to hide or show a border for the view
     */
    var hasBorder: Boolean = false
        set(value) {
            field = value

            handleBorder()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = DEFAULT_INT

    /**
     * Changes the sizes of the add image view
     */
    var imageSize: Int = 0

    /**
     * Changes the paddings of the add image view
     */
    var paddings: Int = 0

    init {

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPMark,
            defStyleAttr
        ) {
            applyMarkAttrs()
        }
    }


    override fun setViewStyle(newStyle: Int) {
        with(newStyle) {
            setBaseViewStyle(this)
            setMarkStyle(this)
        }
    }

    fun setViewData(viewData: SPViewData) {
        val view = createView(viewData)
        removeAllViews()
        addView(view)
        invalidate()
    }

    private fun createView(viewData: SPViewData) = when (viewData) {
        is SPViewData.SPImageUrlData -> {
            viewData.apply {
                roundedCorners = this@SPMark.roundedCorners
            }
        }
        is SPViewData.SPTextData -> {
            viewData.apply {
                textStyle = textAppearance
            }
        }
        is SPViewData.SPImageResourcesData -> {
            viewData.apply {
                tintColor = context.getColorFromAttribute(R.attr.brand_primary)
                params = SPViewData.SPViewDataParams().apply {
                    height = imageSize
                    width = imageSize
                    paddingStart = paddings
                    paddingEnd = paddings
                    paddingTop = paddings
                    paddingBottom = paddings
                }
            }
        }
        else -> viewData
    }.createView(context)

    private fun setMarkStyle(@StyleRes defStyleRes: Int) {
        context.withStyledAttributes(defStyleRes, R.styleable.SPMark) {
            applyMarkAttrs()
        }
    }

    private fun TypedArray.applyMarkAttrs() {
        val chipSize = getDimensionPixelSize(
            R.styleable.SPMark_markHeight, DEFAULT_OBTAIN_VAL
        )
        textAppearance = getResourceId(
            R.styleable.SPMark_android_textAppearance,
            DEFAULT_OBTAIN_VAL
        )
        hasBorder = getBoolean(R.styleable.SPMark_hasBorder, false)
        imageSize = getDimensionPixelSize(
            R.styleable.SPMark_imageSize, DEFAULT_OBTAIN_VAL
        )

        paddings = getDimensionPixelSize(
            R.styleable.SPMark_imagePadding, DEFAULT_OBTAIN_VAL
        )

        setSize(chipSize, chipSize)
    }

    private fun handleBorder() {
        if (hasBorder) {
            val borderSize = resources.getDimensionPixelSize(R.dimen.dimen_p_1)
            val padding = resources.getDimensionPixelSize(R.dimen.dimen_p_0_5)

            setPadding(padding, padding, padding, padding)
            changeBorder(
                context.getColorFromAttribute(R.attr.separator_non_opaque),
                borderSize.toFloat()
            )
        } else {
            shadowRadius = 0f
            changeBorder(DEFAULT_OBTAIN_VAL, DEFAULT_OBTAIN_VAL.toFloat())
        }
    }
}