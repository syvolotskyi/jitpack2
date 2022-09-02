package ge.space.ui.base

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.core.view.children
import ge.space.spaceui.R
import ge.space.ui.util.extension.*
import ge.space.ui.util.path.SPMaskPath
import ge.space.ui.util.path.SPMaskPathRoundedCorners
import kotlin.math.abs

/**
 * Abstract base view which has to be extended. This view allows to change corners radius,
 * add a shadow, change its color, offsets, and alpha. It can both change properties separatly
 * and using a style.
 *
 * @property isCircle instance of [Boolean] which allows to set a shape of view as a circle
 * @property roundedCorners sets [Float] radius value to all corners
 * @property topLeftCornerRadius sets [Float] radius to top left corner
 * @property topRightCornerRadius sets [Float] radius to top right corner
 * @property bottomLeftCornerRadius sets [Float] radius to bottom left corner
 * @property bottomRightCornerRadius sets [Float] radius to bottom right corner
 * @property shadowRadius describes [Float] value of a shadow elevation
 * @property shadowColor sets [Int] value that describes a color of a view shadow
 * @property shadowAlpha sets [Float] value which describes a percentage of the shadow alpha
 * @property shadowOffsetX describes [Float] value which allows to offset the shadow by X
 * @property shadowOffsetY describes [Float] value which allows to offset the shadow by Y
 * @property color describes [Int] value which is a color of the view background
 * @property borderColor describes [Int] value which is a color of the view border
 * @property borderWidth describes [Int] value which is a border width size of the view
 */
abstract class SPBaseView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = 0
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * Paint instance for shadows to avoid
     * creating new instances it each time
     */
    private val shadowPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    /**
     * Paint instance for borders to avoid
     * creating new instances it each time
     */
    private val borderPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        style = Paint.Style.FILL
    }

    /**
     * Path instance for base view shape
     * It gives us possibility to manipulate shape forms
     */
    private val clippingMaskPath: SPMaskPath = SPMaskPathRoundedCorners()

    /**
     * Path instance for base view shape
     * It gives us possibility to manipulate shape forms
     */
    private val bordersClippingMaskPath: SPMaskPath = SPMaskPathRoundedCorners()

    /**
     * Makes a circled shape of the view.
     */
    var isCircle: Boolean = DEFAULT_IS_CIRCLE
        set(value) {
            field = value

            reBuildClippingMaskAndInvalidate()
        }

    /**
     * Sets a radius of all corners for the view.
     */
    protected var roundedCorners: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateRoundRadius()
        }

    /**
     * Sets a radius for top left corner of the view.
     */
    protected var topLeftCornerRadius: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateRoundRadius()
        }

    /**
     * Sets a radius for top right corner of the view.
     */
    protected var topRightCornerRadius: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateRoundRadius()
        }

    /**
     * Sets a radius for bottom right corner of the view.
     */
    protected var bottomRightCornerRadius: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateRoundRadius()
        }

    /**
     * Sets a radius for bottom left corner of the view.
     */
    protected var bottomLeftCornerRadius: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateRoundRadius()
        }

    /**
     * Makes a shadow elevation.
     */
    protected var shadowRadius: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateShadowPaint()
        }

    /**
     * Sets a shadow color.
     */
    protected var shadowColor: Int = Color.BLACK
        set(value) {
            field = value

            invalidateShadowPaint()
        }

    /**
     * Sets a shadow color alpha.
     */
    protected var shadowAlpha: Float = DEFAULT_ALPHA
        set(value) {
            field = value

            shadowColor = wrapAlphaWithColor(value, shadowColor)
        }

    /**
     * Sets an offset for the shadow by X.
     */
    protected var shadowOffsetX: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateShadowPaint()
        }

    /**
     * Sets an offset for the shadow by Y.
     */
    protected var shadowOffsetY: Float = SPMaskPathRoundedCorners.DEFAULT_START_POINT
        set(value) {
            field = value

            invalidateShadowPaint()
        }

    /**
     * Sets a background color.
     */
    var color: Int = Color.WHITE
        set(value) {
            field = value

            shadowPaint.color = value
        }

    /**
     * Border color value
     */
    private var borderColor: Int = Color.TRANSPARENT

    /**
     * Height contains box height + shadow height
     */
    private var contentHeight: Int = DEFAULT_OBTAIN_VAL

    /**
     * Border width value
     */
    private var borderWidth: Float = DEFAULT_OBTAIN_VAL.toFloat()

    init {
        this.setWillNotDraw(false)
        this.setLayerType(LAYER_TYPE_SOFTWARE, null)

        getContext().withStyledAttributes(
            attrs,
            R.styleable.sp_view_style,
            defStyleAttr,
            defStyleRes
        ) {
            withApplyResource()
            withApplyShadowResource()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        if (!changed)
            return

        if (width == 0 || height == 0)
            return

        reBuildClippingMask()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        when {
            // when the height is wrap_content we should wait when view is build to get height and calculate a content height
            isHeightWrapContent() -> {
                super.onMeasure(widthMeasureSpec, heightMeasureSpec)
                viewTreeObserver.addOnGlobalLayoutListener {
                    if (canCalculateContentHeight()) {
                        contentHeight = measuredHeight + shadowOffsetY.toInt()
                        setHeight(contentHeight)
                    }
                }
            }
            // if height is specific check if content height wasn't calculated, if not do it
            canCalculateContentHeight() -> {
                contentHeight = MeasureSpec.getSize(heightMeasureSpec) + shadowOffsetY.toInt()
                setMeasuredDimension(widthMeasureSpec, measureDimension(contentHeight))
                setHeight(contentHeight)
            }
            else -> super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }

        // calculate additional bottom margin if we have shadow y offset**/
        checkShadowMarginContent()
    }

    private fun canCalculateContentHeight(): Boolean = contentHeight == 0 && shadowOffsetY != 0f

    /**
     * Measure desiredSize in pixels compare to MeasureSpec.EXACTLY mode.
     * Returns a MeasureSpec [int]
     */
    private fun measureDimension(desiredSize: Int): Int {
        val measureSpec = MeasureSpec.makeMeasureSpec(desiredSize, MeasureSpec.EXACTLY)

        val specMode = MeasureSpec.getMode(measureSpec)
        val specSize = MeasureSpec.getSize(measureSpec)

        return when (specMode) {
            MeasureSpec.EXACTLY -> specSize
            MeasureSpec.AT_MOST -> desiredSize.coerceAtMost(specSize)
            else -> desiredSize
        }
    }

    fun changeBorder(borderColor: Int, borderWidth: Float) {
        this.borderColor = borderColor
        this.borderWidth = borderWidth
        invalidate()
    }

    /** Calculate additional bottom margin if we have shadow y offset **/
    private fun checkShadowMarginContent() {
        if (measuredHeight > 0 && measuredWidth > 0 ) {
            children.forEach { childView ->
                handleShadowOffset(childView)
            }
        }
    }

    private fun handleShadowOffset(view: View) {
        if (shadowOffsetY > 0f || shadowOffsetX > 0f) {
            val viewParams = view.layoutParams as LayoutParams
            handleShadowOffsetY(viewParams)
            handleShadowOffsetX(viewParams)
            view.layoutParams = viewParams
        }
    }

    private fun handleShadowOffsetX(viewParams: LayoutParams) {
        val ratioOffsetX = shadowOffsetX.withSideRatio()
        if (ratioOffsetX < DEFAULT_OBTAIN_VAL) {
            viewParams.marginStart = abs(ratioOffsetX.toInt())
        } else {
            viewParams.marginEnd = ratioOffsetX.toInt()
        }
    }

    private fun handleShadowOffsetY(viewParams: LayoutParams) {
        val ratioOffsetY = if (isCircle) shadowOffsetY else shadowOffsetY.withSideRatio()
        if (ratioOffsetY < DEFAULT_OBTAIN_VAL) {
            viewParams.topMargin = abs(ratioOffsetY.toInt())
        } else {
            viewParams.bottomMargin = ratioOffsetY.toInt()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        reInitRoundRadius()
        canvas.drawView()
    }

    /**
     * Sets a style for the view.
     *
     * <p>
     * Default style theme is SBBaseView style. A style has to implement SPView styleable
     * attributes. Separate SPBaseView styleable attributes have higher priority than styles.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    open fun setBaseViewStyle(@StyleRes defStyleRes: Int) {
        context.withStyledAttributes(defStyleRes, R.styleable.sp_view_style) {
            withApplyResource()
            withApplyShadowResource()
        }
    }

    /**
     * Sets a shadow style for the view.
     *
     * <p>
     * Default style theme is SBBaseView style. A style has to implement SPView styleable
     * attributes. Separate SPBaseView styleable attributes have higher priority than styles.
     * <p>
     *
     * @param defStyleRes [Int] style resource id
     */
    open fun setBaseViewShadowStyle(@StyleRes defStyleRes: Int) {
        context.withStyledAttributes(defStyleRes, R.styleable.sp_view_style) {
            withApplyShadowResource()
        }
    }

    private fun TypedArray.withApplyResource() {
        color = getColor(R.styleable.sp_view_style_backgroundColor, Color.WHITE)
        topLeftCornerRadius = getDimensionPixelSize(
            R.styleable.sp_view_style_topLeftCornerRadius,
            DEFAULT_OBTAIN_VAL
        ).toFloat()


        topRightCornerRadius = getDimensionPixelSize(
            R.styleable.sp_view_style_topRightCornerRadius,
            DEFAULT_OBTAIN_VAL
        ).toFloat()
        bottomRightCornerRadius = getDimensionPixelSize(
            R.styleable.sp_view_style_bottomRightCornerRadius,
            DEFAULT_OBTAIN_VAL
        ).toFloat()
        bottomLeftCornerRadius = getDimensionPixelSize(
            R.styleable.sp_view_style_bottomLeftCornerRadius,
            DEFAULT_OBTAIN_VAL
        ).toFloat()
        roundedCorners = getDimensionPixelSize(
            R.styleable.sp_view_style_roundedCorners, DEFAULT_OBTAIN_VAL
        ).toFloat()
        isCircle = getBoolean(
            R.styleable.sp_view_style_isCircle, DEFAULT_IS_CIRCLE
        )
    }


    /**
     * get a shadow attributes from TypedArray and implement it.
     *
     */
    private fun TypedArray.withApplyShadowResource() {
        shadowColor = getColor(R.styleable.sp_view_style_shadowColor, Color.BLACK)
        shadowAlpha = getFraction(
            R.styleable.sp_view_style_shadowAlpha, ALPHA_BASE, ALPHA_BASE, DEFAULT_ALPHA
        )
            .scaleTo(ALPHA_SCALE)
        shadowRadius = getDimensionPixelSize(
            R.styleable.sp_view_style_shadowRadius, DEFAULT_OBTAIN_VAL
        ).toFloat()
        shadowOffsetX = getDimensionPixelSize(
            R.styleable.sp_view_style_shadowOffsetX, DEFAULT_OBTAIN_VAL
        ).toFloat()
        shadowOffsetY = getDimensionPixelSize(
            R.styleable.sp_view_style_shadowOffsetY, DEFAULT_OBTAIN_VAL
        ).toFloat()
    }

    private fun getAlpha(shadowAlpha: Float) =
        (COLOR_BYTE * shadowAlpha).toInt() and DEF_COLOR shl A_SHL

    private fun getRed(color: Int) =
        Color.red(color) and DEF_COLOR shl R_SHL

    private fun getGreen(color: Int) =
        Color.green(color) and DEF_COLOR shl G_SHL

    private fun getBlue(color: Int) =
        Color.blue(color) and DEF_COLOR

    private fun wrapAlphaWithColor(alpha: Float, color: Int) =
        getAlpha(alpha) or getRed(color) or getGreen(color) or getBlue(color)

    private fun invalidateShadowPaint() {
        shadowPaint.setShadowLayer(
            shadowRadius.withSideRatio(),
            shadowOffsetX.withSideRatio(),
            shadowOffsetY.withSideRatio(),
            shadowColor
        )
        invalidate()
    }

    private fun invalidateRoundRadius() {
        reInitRoundRadius()
        reBuildClippingMaskAndInvalidate()
    }

    private fun reInitRoundRadius() {
        clippingMaskPath.setRadius(
            getInsideCornerRadius(topLeftCornerRadius),
            getInsideCornerRadius(topRightCornerRadius),
            getInsideCornerRadius(bottomRightCornerRadius),
            getInsideCornerRadius(bottomLeftCornerRadius)
        )

        bordersClippingMaskPath.setRadius(
            getCornerRadius(topLeftCornerRadius),
            getCornerRadius(topRightCornerRadius),
            getCornerRadius(bottomRightCornerRadius),
            getCornerRadius(bottomLeftCornerRadius)
        )

        reBuildClippingMask()
    }

    private fun Canvas.drawView() {
        drawBorder(bordersClippingMaskPath.getPath(), borderColor, borderWidth, borderPaint)
        drawPath(clippingMaskPath.getPath(), shadowPaint)
        clipPath(clippingMaskPath.getPath())
        clipPath(bordersClippingMaskPath.getPath())
    }

    private fun reBuildClippingMaskAndInvalidate() {
        reBuildClippingMask()
        invalidate()
    }

    private fun reBuildClippingMask() {
        if (isCircle) {
            reBuildClippingCircleMask()
        } else {
            reBuildClippingSquareMask()
        }
    }

    private fun reBuildClippingCircleMask() {
        clippingMaskPath.circle(
            ((measuredWidth - borderWidth * SIDE_RATIO) / SIDE_RATIO),
            shadowRadius
        )
        clippingMaskPath.getPath().offset(borderWidth, borderWidth)

        bordersClippingMaskPath.circle(
            (measuredWidth / SIDE_RATIO.toFloat()),
            shadowRadius
        )
    }

    private fun reBuildClippingSquareMask() {
        clippingMaskPath.rebuildPath(
            (measuredWidth - borderWidth * SIDE_RATIO).toInt(),
            (measuredHeight - borderWidth * SIDE_RATIO).toInt(),
            shadowRadius,
            shadowOffsetX,
            shadowOffsetY
        )
        clippingMaskPath.getPath().offset(borderWidth, borderWidth)

        bordersClippingMaskPath.rebuildPath(
            measuredWidth,
            measuredHeight,
            shadowRadius,
            shadowOffsetX,
            shadowOffsetY
        )
    }

    private fun getCornerRadius(cornerRadius: Float) =
        if (cornerRadius > SPMaskPathRoundedCorners.DEFAULT_START_POINT) cornerRadius
        else roundedCorners

    private fun getInsideCornerRadius(cornerRadius: Float): Float {
        val radius = if (cornerRadius > SPMaskPathRoundedCorners.DEFAULT_START_POINT) cornerRadius
        else roundedCorners

        return if (borderWidth != EMPTY_BORDER_VALUE.toFloat() && borderWidth != 0f) {
            val difference = radius - borderWidth
            if (difference > 0) difference else 0f
        } else {
            radius
        }
    }

    companion object {
        const val SIDE_RATIO = 2
        const val SQUARE_RATIO = 4
        const val DEFAULT_INT = 0

        const val EMPTY_BORDER_VALUE = -1
        const val NO_OBTAIN_VAL = -1
        const val DEFAULT_OBTAIN_VAL = 0
        private const val ALPHA_BASE = 1
        private const val ALPHA_SCALE = 2
        private const val DEFAULT_ALPHA = 1f
        private const val DEFAULT_IS_CIRCLE = false

        private const val DEF_COLOR = 0xff
        private const val A_SHL = 24
        private const val R_SHL = 16
        private const val G_SHL = 8
        private const val COLOR_BYTE = 255
    }
}