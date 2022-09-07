package ge.space.ui.components.tooltips

import android.content.Context
import android.content.res.TypedArray
import android.graphics.*
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTooltipLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.tooltips.SPTooltipView.ArrowDirection.*
import ge.space.ui.util.extension.*
import ge.space.ui.util.path.SPMaskPath
import ge.space.ui.util.path.SPMaskPathRoundedCorners

class SPTooltipView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTooltipDefault
) : FrameLayout(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpTooltipLayoutBinding.inflate(LayoutInflater.from(context), this, true)
    }

    /**
     * Sets a component title.
     */
    var text: String = EMPTY_TEXT
        set(value) {
            field = value

            binding.titleTV.text = value
        }

    /**
     * Sets a arrow direction.
     */
    var arrowDirection: ArrowDirection = None
        set(value) {
            field = value

            requestLayout()
        }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var textAppearance: Int = 0
        set(value) {
            field = value
            updateTextAppearance()
        }


    /**
     * Paint instance for shapes to avoid
     * creating new instances it each time
     */
    private val paint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        isAntiAlias = true
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    /**
     * Path instance for base view shape
     * It gives us possibility to manipulate shape forms
     */
    private val clippingMaskPath: SPMaskPath = SPMaskPathRoundedCorners()
    private var triangle: Path = Path()
    private var cornerRadius: Float = resources.getDimensionPixelSize(R.dimen.dimen_p_10).toFloat()
    private var arrowHeight: Float = resources.getDimensionPixelSize(R.dimen.dimen_p_10).toFloat()
    private var borderArrowMargin: Float =
        resources.getDimensionPixelSize(R.dimen.dimen_p_27).toFloat()
    private var arrowWidth: Float = resources.getDimensionPixelSize(R.dimen.dimen_p_18).toFloat()
    private var addedPaddings: Boolean = false

    init {
        this.setWillNotDraw(false)
        this.setLayerType(LAYER_TYPE_SOFTWARE, null)

        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTooltipView,
            defStyleAttr,
            defStyleRes
        ) {
            applyTooltipStyledAttrs()
        }
    }

    private fun TypedArray.applyTooltipStyledAttrs() {
        text = getString(R.styleable.SPTooltipView_android_text).orEmpty()

        getResourceId(
            R.styleable.SPTooltipView_android_textAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
            textAppearance = it
        }

        getColor(
            R.styleable.SPTooltipView_backgroundBoxColor,
            SPBaseView.DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) { paint.color = it }

        val direction = getInt(
            R.styleable.SPTooltipView_directionArrow,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )

        arrowDirection = values()[direction]
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        viewTreeObserver.addOnGlobalLayoutListener {
            if (!addedPaddings)
                binding.titleTV.setPadding(
                    getStartContentPadding(),
                    getTopContentPadding(),
                    getEndContentPadding(),
                    getBottomContentPadding()
                )
            addedPaddings = true
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        reBuildShapePath()
        reBuildArrowPath()
        canvas.drawView()
    }

    /**
     * Sets title text appearance
     */
    fun updateTextAppearance() =
        binding.titleTV.setTextStyle(textAppearance)

    override fun setViewStyle(newStyle: Int) {
        context.withStyledAttributes(
            newStyle,
            R.styleable.SPEmptyStyleView
        ) {
            applyTooltipStyledAttrs()
        }
    }

    private fun reBuildShapePath() {
        with(clippingMaskPath) {
            setRadius(cornerRadius, cornerRadius, cornerRadius, cornerRadius)

            val pathHeight =
                if (isArrowVertical()) measuredHeight - arrowHeight.toInt() else measuredHeight
            val pathWidth =
                if (isArrowHorizontal()) measuredWidth - arrowHeight.toInt() else measuredWidth

            rebuildPath(pathWidth, pathHeight)

            getPath().offset(
                if (arrowDirection == Left) arrowHeight else SPBaseView.DEFAULT_FLOAT,
                if (isArrowOnTop()) arrowHeight else SPBaseView.DEFAULT_FLOAT
            )
        }
    }

    private fun reBuildArrowPath() {
        if (!isArrowHorizontal())
            triangle.buildVerticalArrow(getTriangleX(), getTriangleY(), !isArrowOnTop())
        else {
            triangle.buildHorizontalArrow(getTriangleX(), getTriangleY(), arrowDirection != Left)
        }
    }

    private fun getTriangleX(): Float =
        when (arrowDirection) {
            BottomCenter -> getCentralX()
            BottomRight -> width - borderArrowMargin
            BottomLeft -> borderArrowMargin
            None -> getCentralX()
            Left -> arrowHeight
            Right -> width - arrowHeight
            TopCenter -> getCentralX()
            TopLeft -> borderArrowMargin
            TopRight -> width - borderArrowMargin

        }

    private fun getTriangleY(): Float =
        when (arrowDirection) {
            BottomCenter -> height - arrowHeight
            BottomLeft -> height - arrowHeight
            BottomRight -> height - arrowHeight
            None -> getCentralY()
            Right -> getCentralY()
            Left -> getCentralY()
            TopCenter -> arrowHeight
            TopLeft -> arrowHeight
            TopRight -> arrowHeight
        }

    private fun getCentralY(): Float = height.toFloat().withSideRatio()

    private fun getCentralX(): Float = width.toFloat().withSideRatio()

    private fun isArrowHorizontal(): Boolean =
        arrowDirection == Left || arrowDirection == Right

    private fun isArrowVertical(): Boolean =
        arrowDirection != Left && arrowDirection != Right && arrowDirection != None

    private fun isArrowOnTop(): Boolean =
        arrowDirection == TopCenter || arrowDirection == TopLeft || arrowDirection == TopRight

    private fun getStartContentPadding(): Int =
        if (arrowDirection == Left) binding.titleTV.paddingStart + arrowHeight.toInt() else binding.titleTV.paddingStart

    private fun getTopContentPadding(): Int =
        if (isArrowOnTop()) binding.titleTV.paddingTop + arrowHeight.toInt() else binding.titleTV.paddingTop

    private fun getEndContentPadding(): Int =
        if (arrowDirection == Right) binding.titleTV.paddingEnd + arrowHeight.toInt() else binding.titleTV.paddingEnd

    private fun getBottomContentPadding(): Int =
        if (isArrowVertical() && !isArrowOnTop()) binding.titleTV.paddingBottom + arrowHeight.toInt() else binding.titleTV.paddingTop



    private fun Canvas.drawView() {
        drawPath(clippingMaskPath.getPath(), paint)
        drawPath(triangle, paint)
        clipPath(clippingMaskPath.getPath())
    }

    private fun Path.buildVerticalArrow(x: Float, y: Float, inverted: Boolean): Path {
        val p1 = PointF(x - arrowWidth.withSideRatio(), y)
        val pointY = if (inverted) y + arrowHeight else y - arrowHeight
        val p2 = PointF(x, pointY)
        val p3 = PointF(x + arrowWidth.withSideRatio(), y)

        return apply {
            moveTo(p1.x, p1.y)
            lineTo(p2.x, p2.y)
            lineTo(p3.x, p3.y)
            close()
        }
    }

    private fun Path.buildHorizontalArrow(x: Float, y: Float, inverted: Boolean): Path {
        val p1 = PointF(x, y - arrowWidth.withSideRatio())
        val pointX = if (inverted) x + arrowHeight else x - arrowHeight
        val p2 = PointF(pointX, y)
        val p3 = PointF(x, y + arrowWidth.withSideRatio())

        return apply {
            moveTo(p1.x, p1.y)
            lineTo(p2.x, p2.y)
            lineTo(p3.x, p3.y)
            close()
        }
    }

    /**
     * Enum class which is for arrow direction.
     *
     * @property None applies a box without an arrow.
     * @property TopCenter applies an arrow top center from the view
     * @property TopLeft applies a box with an arrow top left from the view.
     * @property TopRight applies a box with an arrow top right from the view.
     * @property BottomCenter applies a box with an arrow bottom center from the view.
     * @property BottomLeft applies a box with an arrow bottom left from the view.
     * @property BottomRight applies a box with an arrow bottom right from the view.
     * @property Right applies a box with an arrow right from the view.
     * @property Left applies a box with an arrow left from the view.
     */
    enum class ArrowDirection {
        None,
        TopCenter,
        TopLeft,
        TopRight,
        BottomCenter,
        BottomLeft,
        BottomRight,
        Left,
        Right,
    }
}