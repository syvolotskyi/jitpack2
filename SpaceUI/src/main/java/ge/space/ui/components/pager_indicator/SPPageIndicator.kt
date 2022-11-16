package ge.space.ui.components.pager_indicator

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
import ge.space.ui.components.pager_indicator.callbacks.SPPageChangeCallback
import ge.space.ui.components.pager_indicator.callbacks.SPRecyclerScrollListener
import ge.space.ui.components.pager_indicator.callbacks.SPOnPageChangeListener
import ge.space.ui.util.extension.getColorFromAttribute
import kotlin.math.abs

class SPPageIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defResStyle: Int = R.style.SPPageIndicator
) : View(context, attrs, defStyle, defResStyle) {


    private val DEFAULT_DOT_COUNT = 5
    private val DEFAULT_FADING_DOT_COUNT = 1

    private var indicatorHelper: SPPagerIndicatorHelper? = null

    private var dotCount = DEFAULT_DOT_COUNT
    private var fadingDotCount = DEFAULT_FADING_DOT_COUNT
    private var selectedDotSizePx = 0

    private var dotSizePx = 0
    private var dotSeparationDistancePx = 0

    @ColorInt
    private var dotColor: Int = context.getColorFromAttribute(R.attr.brand_secondary)

    @ColorInt
    private var selectedDotColor: Int = context.getColorFromAttribute(R.attr.brand_primary)
    private var selectedDotPaint: Paint
    private var dotPaint: Paint

    /**
     * The current pager position. Used to draw the selected dot if different size/color.
     */
    internal var selectedItemPosition: Int = 0

    /**
     * A temporary value used to reflect changes/transition from one selected item to the next.
     */
    internal var intermediateSelectedItemPosition: Int = 0

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

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        removeAllSources()

        indicatorHelper = SPRecyclerViewHelper(
            recyclerView,
            SPRecyclerScrollListener(recyclerView, this)
        )
    }

    fun attachToViewPager(viewPager: ViewPager) {
        removeAllSources()
        indicatorHelper = SPViewPagerHelper(viewPager, SPOnPageChangeListener(this))

        selectedItemPosition = viewPager.currentItem
    }

    fun attachToViewPager2(viewPager2: ViewPager2) {
        removeAllSources()
        indicatorHelper = SPViewPager2Strategy(viewPager2, SPPageChangeCallback(this))

        selectedItemPosition = viewPager2.currentItem
    }

    fun setDotCount(count: Int) {
        dotCount = count
        invalidate()
    }

    fun setFadingDotCount(count: Int) {
        fadingDotCount = count
        invalidate()
    }

    fun setSelectedDotRadius(radius: Int) {
        selectedDotSizePx = radius
        invalidate()
    }

    fun setDotRadius(radius: Int) {
        dotSizePx = radius
        invalidate()
    }

    fun setDotSeparationDistance(distance: Int) {
        dotSeparationDistancePx = distance
        invalidate()
    }

    fun setDotColor(@ColorInt newDotColor: Int) {
        dotColor = newDotColor
        dotPaint.color = dotColor
        invalidate()
    }

    fun setSelectedDotColor(@ColorInt newSelectedDotColor: Int) {
        selectedDotColor = newSelectedDotColor
        selectedDotPaint.color = selectedDotColor
        invalidate()
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
}
