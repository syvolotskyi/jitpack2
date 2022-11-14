package ge.space.ui.components.stepper

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
import ge.space.ui.util.extension.getColorFromAttribute
import kotlin.math.abs

class SPStepper @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defResStyle: Int = R.style.SPStepper
) : View(context, attrs, defStyle), ViewPager.OnPageChangeListener {

    // region Members

    private val DEFAULT_DOT_COUNT = 5
    private val DEFAULT_FADING_DOT_COUNT = 1


    private var recyclerView: RecyclerView? = null
    private var viewPager: ViewPager? = null
    private var viewPager2: ViewPager2? = null
    private var internalRecyclerScrollListener: SPInternalRecyclerScrollListener? = null
    private var internalPageChangeCallback: SPInternalPageChangeCallback? = null
    private val interpolator = DecelerateInterpolator()

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
            R.styleable.SPStepper,
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
            R.styleable.SPStepper_dotCount,
            DEFAULT_DOT_COUNT
        )
        fadingDotCount = getInt(
            R.styleable.SPStepper_fadingDotCount,
            DEFAULT_FADING_DOT_COUNT
        )
        dotSizePx = getDimensionPixelSize(
            R.styleable.SPStepper_dotRadius,
            dotSizePx
        )
        selectedDotSizePx = getDimensionPixelSize(
            R.styleable.SPStepper_selectedDotRadius,
            selectedDotSizePx
        )
        dotColor = getColor(
            R.styleable.SPStepper_dotColor,
            dotColor
        )
        selectedDotColor = getColor(
            R.styleable.SPStepper_selectedDotColor,
            selectedDotColor
        )
        dotSeparationDistancePx = getDimensionPixelSize(
            R.styleable.SPStepper_dotSeparation,
            dotSeparationDistancePx
        )
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        (0 until getItemCount())
            .map { position ->
                getDotCoordinate(
                    position = position
                )
            }
            .forEach { coordinate ->
                val (xPosition: Float, yPosition: Float) = getXYPositionsByCoordinate(
                    coordinate = coordinate
                )
                canvas.drawCircle(
                    xPosition,
                    yPosition,
                    getRadius(coordinate = coordinate),
                    getPaint(coordinate = coordinate)
                )
            }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val minimumViewSize = selectedDotSizePx

        setMeasuredDimension(getCalculatedWidth(), minimumViewSize)

    }

    fun attachToRecyclerView(recyclerView: RecyclerView) {
        removeAllSources()

        this.recyclerView = recyclerView

        SPInternalRecyclerScrollListener(recyclerView, this).let { newScrollListener ->
            internalRecyclerScrollListener = newScrollListener
            this.recyclerView?.addOnScrollListener(newScrollListener)
        }
    }

    fun attachToViewPager(viewPager: ViewPager?) {
        removeAllSources()

        this.viewPager = viewPager
        this.viewPager?.addOnPageChangeListener(this)

        selectedItemPosition = viewPager?.currentItem ?: 0
    }

    fun attachToViewPager2(viewPager2: ViewPager2) {
        removeAllSources()

        this.viewPager2 = viewPager2

        SPInternalPageChangeCallback(this).let {
            internalPageChangeCallback = it
            this.viewPager2?.registerOnPageChangeCallback(it)
        }

        selectedItemPosition = this.viewPager2?.currentItem ?: 0
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
                interpolator.getInterpolation(1 - percentTowardsEdge) * (dotSizePx / 2)
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
        internalRecyclerScrollListener?.let {
            recyclerView?.removeOnScrollListener(it)
        }

        this.viewPager?.removeOnPageChangeListener(this)

        internalPageChangeCallback?.let {
            viewPager2?.unregisterOnPageChangeCallback(it)
        }

        recyclerView = null
        viewPager = null
        viewPager2 = null
    }

    private fun getItemCount(): Int = when {
        recyclerView != null -> recyclerView?.adapter?.itemCount ?: 0
        viewPager != null -> viewPager?.adapter?.count ?: 0
        viewPager2 != null -> viewPager2?.adapter?.itemCount ?: 0
        else -> 0
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

        selectedItemPosition = position
        intermediateSelectedItemPosition = position
        offsetPercent = positionOffset * -1

        invalidate()
    }

    override fun onPageSelected(position: Int) {
        intermediateSelectedItemPosition = selectedItemPosition
        selectedItemPosition = position

        invalidate()
    }

    override fun onPageScrollStateChanged(state: Int) {
        // Not implemented
    }

}
