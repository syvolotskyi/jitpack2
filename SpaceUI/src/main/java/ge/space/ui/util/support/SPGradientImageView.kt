package ge.space.ui.util.support

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.appcompat.widget.AppCompatImageView
import ge.space.ui.components.bank_cards.data.SPBankCardGradient

/**
 * A sub view which allows to set a background of [SPBankCardView] most comfortable.
 * The gradient of the sub view can be:
 *
 * <p>
 *     1. SPBankCardGradient.SPNoneGradient
 *     2. SPBankCardGradient.SPRadial
 *     3. SPBankCardGradient.SPLinear
 * <p>
 *
 * @property gradient a [SPBankCardGradient] instance which applies a gradient. It can
 * be next types:
 *
 * 1. [SPBankCardGradient.SPNoneGradient] doesn't contain any gradients. It's just for a
 *      background color applying.
 * 2. [SPBankCardGradient.SPRadial] contains a list of colors which are related to the radial
 *      gradient. The radial gradient is applied by only colors.
 * 3. [SPBankCardGradient.SPLinear] contains a list of colors which are related to the linear
 *      gradient. The linear gradient is applied by both a list of colors and degrees. The degrees
 *      property applies an angle of the gradient.
 */
class SPGradientImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {

    /**
     * Applies a gradient of the background by colors and an angle if the last one is needed.
     */
    var gradient: SPBankCardGradient = SPBankCardGradient.SPNoneGradient()
        set(value) {
            field = value

            postInvalidate()
            requestLayout()
        }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        makeGradient(canvas, gradient)
    }

    private fun makeGradient(canvas: Canvas, cardViewGradient: SPBankCardGradient) {
        when (cardViewGradient) {
            is SPBankCardGradient.SPNoneGradient ->
                setBackgroundColor(cardViewGradient.color)
            is SPBankCardGradient.SPLinear ->
                makeLinearGradient(cardViewGradient, canvas)
            is SPBankCardGradient.SPRadial ->
                makeRadialGradient(cardViewGradient, canvas)
        }
    }

    private fun makeRadialGradient(
        cardViewGradient: SPBankCardGradient.SPRadial,
        canvas: Canvas
    ) {
        if (cardViewGradient.colors.count() >= SIDES_COLORS) {
            val matrix = createRadialGradientMatrix()
            val gradientPaint = createRadialGradientPaint(
                matrix,
                getCenterRadialGradientX(),
                getCenterRadialGradientY(),
                getRadialGradientRadius(),
                cardViewGradient.colors
            )

            canvas.drawPaint(gradientPaint)
        }
    }

    private fun makeLinearGradient(
        cardViewGradient: SPBankCardGradient.SPLinear,
        canvas: Canvas
    ) {
        if (cardViewGradient.colors.count() >= SIDES_COLORS) {
            val gradientMatrix = createLinearGradientMatrix(cardViewGradient.degrees)
            val gradientPaint =
                createLinearGradientPaint(gradientMatrix, cardViewGradient.colors)
            canvas.drawPaint(gradientPaint)
        }
    }

    private fun getCenterRadialGradientX() =
        getCenterWidth()

    private fun getCenterRadialGradientY() =
        getCenterHeight() / RADIAL_GRADIENT_CENTER_Y_RATIO

    private fun getRadialGradientRadius() =
        getCenterWidth() / RADIAL_GRADIENT_RADIUS_RATIO

    private fun createLinearGradientMatrix(degrees: Float): Matrix =
        Matrix().apply {
            setRotate(degrees, getCenterWidth(), getCenterHeight())
        }

    private fun createRadialGradientMatrix() : Matrix =
        Matrix().apply {
            setScale(
                RADIAL_GRADIENT_SCALE_X,
                RADIAL_GRADIENT_SCALE_Y,
                getCenterWidth(),
                getCenterHeight()
            )
        }

    private fun createLinearGradientPaint(matrix: Matrix, colors: ArrayList<Int>): Paint =
        Paint().apply {
            shader = createLinearGradient(
                matrix,
                colors[COLOR_FIRST_IND],
                colors[COLOR_SECOND_IND]
            )
        }

    private fun createRadialGradientPaint(
        matrix: Matrix,
        centerX: Float,
        centerY: Float,
        radius: Float,
        colors: ArrayList<Int>
    ) : Paint =
        Paint().apply {
            shader = createRadialGradient(
                matrix,
                centerX,
                centerY,
                radius,
                colors[COLOR_FIRST_IND],
                colors[COLOR_SECOND_IND],
            )
        }

    private fun createLinearGradient(
        matrix: Matrix,
        color0: Int,
        color1: Int
    ) =
        LinearGradient(
            DEFAULT_DEGREES,
            measuredHeight.toFloat(),
            measuredWidth.toFloat(),
            measuredHeight.toFloat(),
            color0,
            color1,
            Shader.TileMode.CLAMP
        ).apply {
            setLocalMatrix(matrix)
        }

    private fun createRadialGradient(
        matrix: Matrix,
        centerX: Float,
        centerY: Float,
        radius: Float,
        color0: Int,
        color1: Int
    ) =
        RadialGradient(
            centerX,
            centerY,
            radius,
            color0,
            color1,
            Shader.TileMode.CLAMP
        ).apply {
            setLocalMatrix(matrix)
        }

    private fun getCenterWidth() =
        (measuredWidth / SIDES_COLORS).toFloat()

    private fun getCenterHeight() =
        (measuredHeight / SIDES_COLORS).toFloat()

    companion object {
        private const val RADIAL_GRADIENT_SCALE_X = 9f
        private const val RADIAL_GRADIENT_SCALE_Y = 5.7f
        private const val RADIAL_GRADIENT_CENTER_Y_RATIO = 1.25f
        private const val RADIAL_GRADIENT_RADIUS_RATIO = 4

        private const val SIDES_COLORS = 2
        private const val DEFAULT_DEGREES = 0f

        private const val COLOR_FIRST_IND = 0
        private const val COLOR_SECOND_IND = 1
    }
}