package ge.space.ui.util.path

import android.graphics.Path
import android.graphics.RectF
import ge.space.ui.util.extension.withSideRatio

/**
 * Helper which allows to handle [Path] object that is for draw [SPBaseView] views.
 */
class SPMaskPathRoundedCorners(
    topLefRadiusPx: Float = DEFAULT_START_POINT,
    topRightRadiusPx: Float = DEFAULT_START_POINT,
    bottomRightRadiusPx: Float = DEFAULT_START_POINT,
    bottomLefRadiusPx: Float = DEFAULT_START_POINT
) {
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

    /**
     * Sets radii for all corners. After this method path has to be rebuilt by
     * rebuildPath(..) method.
     *
     * @param topLefRadiusPx sets a radius for a top left corner
     * @param topRightRadiusPx sets a radius for a top right corner
     * @param bottomRightRadiusPx sets a radius for a bottom right corner
     * @param bottomLefRadiusPx sets a radius for a bottom left corner
     */
    fun setRadius(
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

    /**
     * Rebuilds a path with already set radii.
     *
     * @param containerWidthPx sets [Int] value for width in Px
     * @param containerHeightPx sets [Int] value for height in Px
     * @param radiusShadow sets an elevation for the view
     * @param offsetX sets an offset for the shadow by X
     * @param offsetY sets an offset for the shadow by Y
     */
    fun rebuildPath(
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

    private fun getStartPathPoint(radius: Float, offset: Float) =
        DEFAULT_START_POINT + handleNegativeOffset(
            offset = radius.withSideRatio() - offset.withSideRatio()
        )

    private fun getEndPathPoint(containerPx: Float, radius: Float, offset: Float) =
        containerPx - radius.withSideRatio() - handleNegativeOffset(offset).withSideRatio()

    private fun handleNegativeOffset(offset: Float) =
        if (offset < DEFAULT_START_POINT) DEFAULT_START_POINT else offset

    /**
     * Returns a path of a shape.
     *
     * @return [Path] object which describes a shape of a view.
     */
    fun getPath(): Path =
        path

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