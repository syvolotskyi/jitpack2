package ge.space.ui.components.pager_indicator

import SPRecyclerViewHelper
import SPViewPagerHelper
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.annotation.ColorInt
import androidx.core.content.withStyledAttributes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView.Companion.DEFAULT_OBTAIN_VAL
import ge.space.ui.components.feature_list.SPFeatureListItem
import ge.space.ui.components.feature_list.SPFeatureListItem.Orientation
import ge.space.ui.components.pager_indicator.callbacks.SPPageChangeCallback
import ge.space.ui.components.pager_indicator.callbacks.SPRecyclerScrollListener
import ge.space.ui.components.pager_indicator.callbacks.SPOnPageChangeListener
import ge.space.ui.components.pager_indicator.helper.SPViewPager2Helper
import ge.space.ui.util.extension.getColorFromAttribute
import kotlin.math.abs

/**
 * [SPPageIndicator] view extended from View generic that allows to change its configuration.
 * There are 2 realized styles which can be applied to the view:
 *
 * <p>
 *     1. SPPageIndicator
 *     2. SPPageIndicator.Intro
 * <p>
 *
 *
 * @property dotCount [Int] Value which sets total count of visible dots .
 * @property fadingDotCount [Int] Sets total count of visible fading dots.
 * @property selectedDotSizePx [Int] Sets size of the selected dot.
 * @property dotSizePx [Int] Sets size of the unselected dot.
 * @property selectedDotColor [Int] Sets color of the selected dot.
 * @property dotSeparationDistancePx [Int] Distance between dots.
 * @property dotColor [Int]Sets color of the unselected dot.
 */
class SPPageIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defResStyle: Int = R.style.SPPageIndicator
) : View(context, attrs, defStyle, defResStyle) {

    /* Sets total count of visible full dots*/
    var dotCount = DEFAULT_DOT_COUNT
        set(value) {
            field = value
            invalidate()
        }

    /* Sets total count of visible fading dots*/
    var fadingDotCount = DEFAULT_FADING_DOT_COUNT
        set(value) {
            field = value
            invalidate()
        }


    /* Sets size of the selected dot */
    var selectedDotSizePx = DEFAULT_OBTAIN_VAL
        set(value) {
            field = value
            invalidate()
        }

    /* Sets size of the unselected dot */
    var dotSizePx = DEFAULT_OBTAIN_VAL
        set(value) {
            field = value
            invalidate()
        }

    /* Distance between dots */
    var dotSeparationDistancePx = DEFAULT_OBTAIN_VAL
        set(value) {
            field = value
            invalidate()
        }

    /* Sets color of the selected dot */
    @ColorInt
    var selectedDotColor: Int = context.getColorFromAttribute(R.attr.brand_primary)
        set(value) {
            field = value
            selectedDotPaint.color = selectedDotColor
            invalidate()
        }

    /* Sets color of the unselected dot*/
    @ColorInt
    var dotColor: Int = context.getColorFromAttribute(R.attr.brand_secondary)
        set(value) {
            field = value
            dotPaint.color = dotColor
            invalidate()
        }

    private var indicatorHelper: SPPagerIndicatorHelper? = null
    private var selectedDotPaint: Paint
    private var dotPaint: Paint

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

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPPageIndicator,
            defStyle,
            defResStyle
        ) {
            applyStyledAttrs()
        }

        selectedDotPaint = getDefaultPaintConfig(
            defaultColor = selectedDotColor
        )

        dotPaint = getDefaultPaintConfig(
            defaultColor = dotColor
        )
    }

    private fun TypedArray.applyStyledAttrs() {
        dotCount = getInteger(
            R.styleable.SPPageIndicator_dotCount,
            DEFAULT_DOT_COUNT
        )
        fadingDotCount = getInt(
            R.styleable.SPPageIndicator_fadingDotCount,
            DEFAULT_FADING_DOT_COUNT
        )
        dotSizePx = getDimensionPixelSize(
            R.styleable.SPPageIndicator_dotRadius,
            dotSizePx
        )
        selectedDotSizePx = getDimensionPixelSize(
            R.styleable.SPPageIndicator_selectedDotRadius,
            selectedDotSizePx
        )
        dotColor = getColor(
            R.styleable.SPPageIndicator_dotColor,
            dotColor
        )
        selectedDotColor = getColor(
            R.styleable.SPPageIndicator_selectedDotColor,
            selectedDotColor
        )
        dotSeparationDistancePx = getDimensionPixelSize(
            R.styleable.SPPageIndicator_dotSeparation,
            dotSeparationDistancePx
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //draw circles for each item
        (0 until getItemCount())
            //transform position to coordinate
            .map { position -> getDotCoordinate(position) }
            .forEach { coordinate ->
                //get x and y position and draw
                val (xPosition: Float, yPosition: Float) = getXYPositionsByCoordinate(coordinate)
                canvas.drawCircle(
                    xPosition,
                    yPosition,
                    getRadius(coordinate),
                    getPaint(coordinate)
                )
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minimumViewSize = selectedDotSizePx

        setMeasuredDimension(getCalculatedWidth(), minimumViewSize)
    }

    /*  attach view indicator to Recycler View 2*/
    fun attachToRecyclerView(recyclerView: RecyclerView) {
        removeAllSources()

        indicatorHelper = SPRecyclerViewHelper(
            recyclerView,
            SPRecyclerScrollListener(recyclerView, this)
        )
    }

    /*  attach view indicator to view pager */
    fun attachToViewPager(viewPager: ViewPager) {
        removeAllSources()
        indicatorHelper = SPViewPagerHelper(viewPager, SPOnPageChangeListener(this))

        selectedItemPosition = viewPager.currentItem
    }

    /*  attach view indicator to view pager 2*/
    fun attachToViewPager2(viewPager2: ViewPager2) {
        removeAllSources()
        indicatorHelper = SPViewPager2Helper(viewPager2, SPPageChangeCallback(this))

        selectedItemPosition = viewPager2.currentItem
    }

    internal fun onPageScrolled(position: Int, positionOffset: Float) {
        selectedItemPosition = position
        intermediateSelectedItemPosition = position
        offsetPercent = positionOffset * -1
        invalidate()
    }

    internal fun onPageSelected(position: Int) {
        intermediateSelectedItemPosition = selectedItemPosition
        selectedItemPosition = position
        invalidate()
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
        val xPosition: Float = width / 2 + coordinate
        val yPosition: Float = getDotYCoordinate().toFloat()

        return Pair(xPosition, yPosition)
    }

    private fun getDotCoordinate(position: Int): Float =
        (position - intermediateSelectedItemPosition) * getDistanceBetweenTheCenterOfTwoDots() +
                (getDistanceBetweenTheCenterOfTwoDots() * offsetPercent)

    /**
     * Get the y coordinate for a dot.
     *
     * The bottom of the view is y = 0 and a dot is drawn from the center, so therefore
     * the y coordinate is simply the radius.
     */
    private fun getDotYCoordinate(): Int = selectedDotSizePx / 2

    /**
     * Calculates the distance between 2 dots center.
     */
    private fun getDistanceBetweenTheCenterOfTwoDots() = dotSizePx + dotSeparationDistancePx

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
    private fun getRadius(coordinate: Float): Float {
        val coordinateAbs = abs(coordinate)
        // Get the coordinate where dots begin showing as fading dots (x coordinates > half of width of all large dots)
        val largeDotThreshold = dotCount.toFloat() / 2 * getDistanceBetweenTheCenterOfTwoDots()
        return when {
            coordinateAbs < getDistanceBetweenTheCenterOfTwoDots() / 2 -> selectedDotSizePx.toFloat() / 2
            coordinateAbs <= largeDotThreshold -> dotSizePx.toFloat() / 2
            else -> {
                // Determine how close the dot is to the edge of the view for scaling the size of the dot
                val percentTowardsEdge = (coordinateAbs - largeDotThreshold) /
                        (getCalculatedWidth() / 2.01f - largeDotThreshold)
                DecelerateInterpolator().getInterpolation(1 - percentTowardsEdge) * (dotSizePx / 2)
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
    private fun getPaint(coordinate: Float): Paint = when {
        abs(coordinate) < getDistanceBetweenTheCenterOfTwoDots() / 2 -> selectedDotPaint
        else -> dotPaint
    }

    /**
     * Get the calculated width of the view.
     *
     * Calculated by the total number of visible dots (normal & fading).
     *
     */
    private fun getCalculatedWidth(): Int {
        val maxNumVisibleDots = dotCount + 2 * fadingDotCount
        return (maxNumVisibleDots - 1) * getDistanceBetweenTheCenterOfTwoDots() + dotSizePx
    }

    private fun removeAllSources() {
        indicatorHelper?.removeListener()
        indicatorHelper = null
    }

    private fun getItemCount(): Int = indicatorHelper?.getItemCount() ?: 0

    companion object {
        private const val DEFAULT_DOT_COUNT = 5
        private const val DEFAULT_FADING_DOT_COUNT = 1
    }
}
