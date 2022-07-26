package com.space.components.animations.splash

import android.graphics.Path
import android.graphics.PathMeasure
import android.os.Build
import android.view.animation.Interpolator
import androidx.annotation.FloatRange
import java.util.*
import kotlin.math.min

class PathInterpolator : Interpolator {

    private var mX: FloatArray? = null
    private var mY: FloatArray? = null

    constructor(path: Path) {
        initFromPath(path)
    }
    constructor(
        controlX1: Float,
        controlY1: Float,
        controlX2: Float,
        controlY2: Float
    ) {
        initFromPoints(controlX1, controlY1, controlX2, controlY2)
    }

    private fun initFromPoints(
        x1: Float,
        y1: Float,
        x2: Float,
        y2: Float
    ) {
        val path = Path()
        path.moveTo(0f, 0f)
        path.cubicTo(x1, y1, x2, y2, 1f, 1f)
        initFromPath(path)
    }

    private fun initFromPath(path: Path) {
        val pointComponents = PathCompat.approximate(
            path,
            PRECISION
        )

        val numPoints = pointComponents.size / 3
        mX = FloatArray(numPoints)
        mY = FloatArray(numPoints)

        var prevX = 0f
        var prevFraction = 0f
        var componentIndex = 0

        for (i in 0 until numPoints) {
            val fraction = pointComponents[componentIndex++]
            val x = pointComponents[componentIndex++]
            val y = pointComponents[componentIndex++]
            require(!(fraction == prevFraction && x != prevX)) { "The Path cannot have discontinuity in the X axis." }
            require(x >= prevX) { "The Path cannot loop back on itself." }
            mX!![i] = x
            mY!![i] = y
            prevX = x
            prevFraction = fraction
        }
    }

    override fun getInterpolation(t: Float): Float {
        if (t <= 0) {
            return 0f
        } else if (t >= 1) {
            return 1f
        }

        var startIndex = 0
        var endIndex = mX!!.size - 1

        while (endIndex - startIndex > 1) {
            val midIndex = (startIndex + endIndex) / 2
            if (t < mX!![midIndex]) {
                endIndex = midIndex
            } else {
                startIndex = midIndex
            }
        }

        val xRange = mX!![endIndex] - mX!![startIndex]
        if (xRange == 0f) {
            return mY!![startIndex]
        }
        val tInRange = t - mX!![startIndex]
        val fraction = tInRange / xRange
        val startY = mY!![startIndex]
        val endY = mY!![endIndex]

        return startY + fraction * (endY - startY)
    }

    object PathCompat {
        private const val MAX_NUM_POINTS = 100
        private const val FRACTION_OFFSET = 0
        private const val X_OFFSET = 1
        private const val Y_OFFSET = 2
        private const val NUM_COMPONENTS = 3


        fun approximate(path: Path, @FloatRange(from = 0.0) acceptableError: Float): FloatArray {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                return path.approximate(acceptableError)
            }

            require(acceptableError >= 0) { "acceptableError must be greater than or equal to 0" }

            val measureForTotalLength = PathMeasure(path, false)
            var totalLength = 0f
            val summedContourLengths: MutableList<Float> = ArrayList()
            summedContourLengths.add(0f)

            do {
                val pathLength = measureForTotalLength.length
                totalLength += pathLength
                summedContourLengths.add(totalLength)
            } while (measureForTotalLength.nextContour())

            val pathMeasure = PathMeasure(path, false)
            val numPoints = min(
                MAX_NUM_POINTS,
                (totalLength / acceptableError).toInt() + 1
            )
            val coords = FloatArray(NUM_COMPONENTS * numPoints)
            val position = FloatArray(2)
            var contourIndex = 0
            val step = totalLength / (numPoints - 1)
            var cumulativeDistance = 0f

            for (i in 0 until numPoints) {
                val contourDistance =
                    cumulativeDistance - summedContourLengths[contourIndex]
                pathMeasure.getPosTan(contourDistance, position, null)
                coords[i * NUM_COMPONENTS + FRACTION_OFFSET] =
                    cumulativeDistance / totalLength
                coords[i * NUM_COMPONENTS + X_OFFSET] = position[0]
                coords[i * NUM_COMPONENTS + Y_OFFSET] = position[1]
                cumulativeDistance = Math.min(cumulativeDistance + step, totalLength)
                while (summedContourLengths[contourIndex + 1] < cumulativeDistance) {
                    contourIndex++
                    pathMeasure.nextContour()
                }
            }
            coords[(numPoints - 1) * NUM_COMPONENTS + FRACTION_OFFSET] = 1f

            //Fixing wrong approximate with tangent for low level API devices
            coords[1] = 0f
            coords[2] = 0f
            coords[coords.size - 2] = 1f
            coords[coords.size - 1] = 1f
            return coords
        }
    }

    companion object {
        private val PRECISION = 0.002f
    }
}