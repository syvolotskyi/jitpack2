package ge.space.ui.components.pager_indicator.core

import ge.space.ui.components.pager_indicator.helper.recycler_view.SPPageIndicatorRecyclerViewHelper
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.components.pager_indicator.helper.view_pager.SPVPOnPageChangeListener
import ge.space.ui.components.pager_indicator.helper.view_pager2.SPVP2OnPageChangeCallback
import ge.space.ui.components.pager_indicator.helper.recycler_view.SPRecyclerScrollListener
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorStateHelper
import ge.space.ui.components.pager_indicator.helper.SPPageIndicatorAttachmentType
import ge.space.ui.components.pager_indicator.helper.view_pager.SPPageIndicatorVPHelper
import ge.space.ui.components.pager_indicator.helper.view_pager2.SPPageIndicatorVP2Helper
import ge.space.ui.util.extension.withSideRatio
import kotlin.math.abs


/**
 * [SPPageIndicator] view extended from View generic that allows to change its configuration.
 * There are 2 realized styles which can be applied to the view:
 *
 * * SPPageIndicator
 * * SPPageIndicator.Intro
 *
 */
class SPPageIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defResStyle: Int = R.style.SPPageIndicator
) : View(context, attrs, defStyle, defResStyle) {

    /**
     * [indicatorConfig] consist of by [SPPageIndicator] attributes
     * It is standalone model for [SPPageIndicator] ui configuration
     *
     * @see [SPPageIndicatorConfig] for additional information
     */
    var indicatorConfig = SPPageIndicatorConfig()
        set(value) {
            field = value
            invalidate()
        }

    /**
     * The current pager position. Used to draw the selected dot if different size/color.
     */
    internal var selectedItemPosition: Int = DEFAULT_OBTAIN_VAL

    /**
     * A temporary value used to reflect changes/transition from one selected item to the next.
     */
    internal var intermediateSelectedItemPosition: Int = DEFAULT_OBTAIN_VAL

    /**
     * The scroll percentage of the viewpager or recyclerview.
     * Used for moving the dots/scaling the fading dots.
     */
    internal var offsetPercent: Float = 0f

    /**
     * [pageIndicatorStateHelper] handles attachment([SPPageIndicatorAttachmentType]) states changes
     */
    private var pageIndicatorStateHelper: SPPageIndicatorStateHelper? = null

    /**
     * [selectedDotPaint] defines selected dot paint configuration
     */
    private val selectedDotPaint: Paint by lazy {
        getDefaultPaintConfig(defaultColor = indicatorConfig.selectedDotColor)
    }

    /**
     * [dotPaint] defines unselected dot paint configuration
     */
    private val dotPaint: Paint by lazy {
        getDefaultPaintConfig(defaultColor = indicatorConfig.dotColor)
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPageIndicator,
            defStyle,
            defResStyle
        ) {
            applyStyledAttrs()
        }
    }

    private fun TypedArray.applyStyledAttrs() {
        indicatorConfig = SPPageIndicatorConfig(
            dotCount = getInteger(R.styleable.SPPageIndicator_dotCount, DEFAULT_DOT_COUNT),
            dotColor = getColor(R.styleable.SPPageIndicator_dotColor, R.attr.brand_secondary),
            dotSizePx = getDimensionPixelSize(R.styleable.SPPageIndicator_dotRadius, DEFAULT_OBTAIN_VAL),
            fadingDotCount = getInt(R.styleable.SPPageIndicator_fadingDotCount, DEFAULT_FADING_DOT_COUNT),
            selectedDotColor = getColor(R.styleable.SPPageIndicator_selectedDotColor, R.attr.brand_primary),
            centralizeSelectedDot = getBoolean(R.styleable.SPPageIndicator_centralizeSelectedDot, true),
            selectedDotSizePx = getDimensionPixelSize(R.styleable.SPPageIndicator_selectedDotRadius, DEFAULT_OBTAIN_VAL),
            dotSeparationDistancePx = getDimensionPixelSize(R.styleable.SPPageIndicator_dotSeparation, DEFAULT_OBTAIN_VAL),
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawCircles(canvas)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        setMeasuredDimension(getCalculatedWidth(), indicatorConfig.selectedDotSizePx)
    }

    /**
     * [drawCircles] draws circles for each item
     *
     * @param canvas view canvas
     */
    private fun drawCircles(canvas: Canvas) = repeat(pageIndicatorStateHelper?.getItemCount() ?: 0) { dotPosition ->
        //transform position to coordinate
        val coordinate = getDotCoordinate(dotPosition)
        //get x and y position and draw
        val (xPosition: Float, yPosition: Float) = getXYPositionsByCoordinate(coordinate)
        canvas.drawCircle(xPosition, yPosition, getRadius(dotPosition, coordinate), getPaint(dotPosition, coordinate))
    }

    /**
     * [attachTo] removes old pageIndicatorStateHelper and sets new [attachmentType]
     *
     * @param attachmentType is a [SPPageIndicatorAttachmentType]
     */
    fun attachTo(attachmentType: SPPageIndicatorAttachmentType){
        pageIndicatorStateHelper?.removeListener()

        pageIndicatorStateHelper = when(attachmentType){
            is SPPageIndicatorAttachmentType.SPViewPager -> {
                selectedItemPosition = attachmentType.viewPager.currentItem
                SPPageIndicatorVPHelper(attachmentType.viewPager, SPVPOnPageChangeListener(this))
            }
            is SPPageIndicatorAttachmentType.SPViewPager2 -> {
                selectedItemPosition = attachmentType.viewPager2.currentItem
                SPPageIndicatorVP2Helper(attachmentType.viewPager2, SPVP2OnPageChangeCallback(this))
            }
            is SPPageIndicatorAttachmentType.SPRecyclerView -> {
                SPPageIndicatorRecyclerViewHelper(attachmentType.recyclerView, SPRecyclerScrollListener(attachmentType.recyclerView, this))
            }
        }
    }

    private fun getDefaultPaintConfig(
        defaultStyle: Paint.Style = Paint.Style.FILL,
        isAntiAliasDefault: Boolean = true,
        @ColorInt defaultColor: Int
    ): Paint = Paint().apply {
        style = defaultStyle
        isAntiAlias = isAntiAliasDefault
        color = defaultColor
    }

    private fun getXYPositionsByCoordinate(coordinate: Float): Pair<Float, Float> {
        val xPosition: Float = if (indicatorConfig.centralizeSelectedDot) width.toFloat().withSideRatio() + coordinate else coordinate
        val yPosition: Float = getDotYCoordinate().toFloat()

        return Pair(xPosition, yPosition)
    }

    private fun getDotCoordinate(dotPosition: Int): Float = when {
        !indicatorConfig.centralizeSelectedDot -> {
            (getDistanceBetweenTheCenterOfTwoDots() * dotPosition) + indicatorConfig.dotSizePx.toFloat().withSideRatio()
        }
        else -> {
            ((dotPosition - intermediateSelectedItemPosition) * getDistanceBetweenTheCenterOfTwoDots() + (getDistanceBetweenTheCenterOfTwoDots() * offsetPercent))
        }
    }

    /**
     * Get the y coordinate for a dot.
     *
     * The bottom of the view is y = 0 and a dot is drawn from the center, so therefore
     * the y coordinate is simply the radius.
     */
    private fun getDotYCoordinate(): Int = indicatorConfig.selectedDotSizePx / 2

    /**
     * Calculates the distance between 2 dots center.
     */
    private fun getDistanceBetweenTheCenterOfTwoDots() = indicatorConfig.dotSizePx + indicatorConfig.dotSeparationDistancePx

    /**
     * Calculates a dot radius based on its position.
     *
     * If the position is within 1 dot length, it's the currently selected dot.
     *
     * If the position is within a threshold (half the width of the number of non fading dots),
     * it is a normal sized dot.
     *
     * If the position is outside of the above threshold, it is a fading dot or not visible. The
     * radius is calculated based on a interpolator percentage of how far the
     * viewpager/recyclerview has scrolled.
     */
    private fun getRadius(position:Int, coordinate: Float): Float {
        val coordinateAbs = abs(coordinate)
        // Get the coordinate where dots begin showing as fading dots (x coordinates > half of width of all large dots)
        val largeDotThreshold = indicatorConfig.dotCount.toFloat().withSideRatio() * getDistanceBetweenTheCenterOfTwoDots()
        return when {
            !indicatorConfig.centralizeSelectedDot && position == selectedItemPosition -> indicatorConfig.selectedDotSizePx.toFloat().withSideRatio()
            !indicatorConfig.centralizeSelectedDot && position != selectedItemPosition -> indicatorConfig.dotSizePx.toFloat().withSideRatio()
            coordinateAbs < getDistanceBetweenTheCenterOfTwoDots() / 2 -> indicatorConfig.selectedDotSizePx.toFloat().withSideRatio()
            coordinateAbs <= largeDotThreshold -> indicatorConfig.dotSizePx.toFloat().withSideRatio()
            else -> {
                // Determine how close the dot is to the edge of the view for scaling the size of the dot
                val percentTowardsEdge = (coordinateAbs - largeDotThreshold) / (getCalculatedWidth() / 2.01f - largeDotThreshold)
                DecelerateInterpolator().getInterpolation(1 - percentTowardsEdge) * (indicatorConfig.dotSizePx / 2)
            }
        }
    }

    /**
     * Returns the dot's color based on coordinate, similar to {@link #getRadius(Float)}.
     *
     * If the position is within 1 dot's length of x or y = 0, it is the currently selected dot.
     *
     * All other dots will be the normal specified dot color.
     */
    private fun getPaint(position: Int, coordinate: Float): Paint = when {
        !indicatorConfig.centralizeSelectedDot && position == selectedItemPosition-> selectedDotPaint
        !indicatorConfig.centralizeSelectedDot && position != selectedItemPosition-> dotPaint
        abs(coordinate) < getDistanceBetweenTheCenterOfTwoDots() / 2 -> selectedDotPaint
        else -> dotPaint
    }

    /**
     * Get the calculated width of the view.
     * Calculated by the total number of visible dots (normal & fading).
     */
    private fun getCalculatedWidth(): Int {
        val maxNumVisibleDots = indicatorConfig.dotCount + 2 * indicatorConfig.fadingDotCount
        return (maxNumVisibleDots - 1) * getDistanceBetweenTheCenterOfTwoDots() + indicatorConfig.dotSizePx
    }

    companion object {
        internal const val DEFAULT_DOT_COUNT = 5
        internal const val DEFAULT_FADING_DOT_COUNT = 1
    }
}
