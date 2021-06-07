package ge.space.ui.util.path

import android.graphics.Path
import android.graphics.RectF
import ge.space.ui.base.SPBaseView
import ge.space.ui.util.extension.withSideRatio

/**
 * Helper which allows to handle [Path] object that is for draw [SPBaseView] views.
 */
class SPMaskPathRoundedCorners(
    topLefRadiusPx: Float = DEFAULT_START_POINT,
    topRightRadiusPx: Float = DEFAULT_START_POINT,
    bottomRightRadiusPx: Float = DEFAULT_START_POINT,
    bottomLefRadiusPx: Float = DEFAULT_START_POINT
) : SPMaskPath {

    private val path = Path()

    private val radii = floatArrayOf(
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT,
        DEFAULT_START_POINT
    )

    init {
        setRadius(topLefRadiusPx, topRightRadiusPx, bottomRightRadiusPx, bottomLefRadiusPx)
    }

    override fun setRadius(
        topLefRadiusPx: Float,
        topRightRadiusPx: Float,
        bottomRightRadiusPx: Float,
        bottomLefRadiusPx: Float
    ) {
        radii[TOP_LEFT_FIRST_RAD] = topLefRadiusPx
        radii[TOP_LEFT_SECOND_RAD] = topLefRadiusPx
        radii[TOP_RIGHT_FIRST_RAD] = topRightRadiusPx
        radii[TOP_RIGHT_SECOND_RAD] = topRightRadiusPx
        radii[BOTTOM_RIGHT_FIRST_RAD] = bottomRightRadiusPx
        radii[BOTTOM_RIGHT_SECOND_RAD] = bottomRightRadiusPx
        radii[BOTTOM_LEFT_FIRST_RAD] = bottomLefRadiusPx
        radii[BOTTOM_LEFT_SECOND_RAD] = bottomLefRadiusPx
    }

    override fun rebuildPath(
        containerWidthPx: Int,
        containerHeightPx: Int,
        radiusShadow: Float,
        offsetX: Float,
        offsetY: Float
    ): Path {
        path.reset()
        path.addRoundRect(
            RectF(
                getStartPathPoint(radiusShadow, offsetX),
                getStartPathPoint(radiusShadow, offsetY),
                getEndPathPoint(containerWidthPx.toFloat(), radiusShadow, offsetX),
                getEndPathPoint(containerHeightPx.toFloat(), radiusShadow, offsetY)
            ),
            radii,
            Path.Direction.CW
        )
        path.close()

        return path
    }

    override fun circle(
        radius: Float,
        radiusShadow: Float
    ) : Path {
        path.reset()
        path.addCircle(
            DEFAULT_START_POINT + radius,
            DEFAULT_START_POINT + radius,
            radius - radiusShadow,
            Path.Direction.CW
        )
        path.close()

        return path
    }

    override fun getPath(): Path =
        path

    private fun getStartPathPoint(radius: Float, offset: Float) =
        DEFAULT_START_POINT + handleNegativeOffset(
            offset = radius.withSideRatio() - offset.withSideRatio()
        )

    private fun getEndPathPoint(containerPx: Float, radius: Float, offset: Float) =
        containerPx - radius.withSideRatio() - handleNegativeOffset(offset).withSideRatio()

    private fun handleNegativeOffset(offset: Float) =
        if (offset < DEFAULT_START_POINT) DEFAULT_START_POINT else offset

    companion object {
        const val DEFAULT_START_POINT = 0f

        private const val TOP_LEFT_FIRST_RAD = 0
        private const val TOP_LEFT_SECOND_RAD = 1
        private const val TOP_RIGHT_FIRST_RAD = 2
        private const val TOP_RIGHT_SECOND_RAD = 3
        private const val BOTTOM_RIGHT_FIRST_RAD = 4
        private const val BOTTOM_RIGHT_SECOND_RAD = 5
        private const val BOTTOM_LEFT_FIRST_RAD = 6
        private const val BOTTOM_LEFT_SECOND_RAD = 7
    }
}