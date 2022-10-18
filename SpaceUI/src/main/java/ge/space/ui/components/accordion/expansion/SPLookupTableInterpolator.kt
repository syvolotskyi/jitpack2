package ge.space.ui.components.accordion.expansion

import android.view.animation.Interpolator

/**
 * An [Interpolator] that uses a lookup table to compute an interpolation based on a
 * given input.
 */
abstract class SPLookupTableInterpolator(private val values: FloatArray) : Interpolator {
    private val stepSize: Float = 1f / (values.size - 1)

    override fun getInterpolation(input: Float): Float {
        if (input >= 1.0f) {
            return 1.0f
        }
        if (input <= 0f) {
            return 0f
        }

        // Calculate index - We use min with length - 2 to avoid IndexOutOfBoundsException when
        // we linearly interpolate in the return statement
        val position = (input * (values.size - 1)).toInt().coerceAtMost(values.size - 2)

        // Calculate values to account for small offsets as the lookup table has discrete values
        val quantized = position * stepSize
        val diff = input - quantized
        val weight = diff / stepSize

        // Linearly interpolate between the table values
        return values[position] + weight * (values[position + 1] - values[position])
    }
}