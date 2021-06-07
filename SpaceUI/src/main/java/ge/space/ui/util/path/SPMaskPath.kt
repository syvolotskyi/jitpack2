package ge.space.ui.util.path

import android.graphics.Path

/**
 * Interface which allows to apply a set of methods to manipulate a path
 */
interface SPMaskPath {

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
    )

    /**
     * Rebuilds a path with already set radii.
     *
     * @param containerWidthPx sets [Int] value for width in Px
     * @param containerHeightPx sets [Int] value for height in Px
     * @param radiusShadow sets an elevation for the view
     * @param offsetX sets an offset for the shadow by X
     * @param offsetY sets an offset for the shadow by Y
     *
     * @return [Path] as a result
     */
    fun rebuildPath(
        containerWidthPx: Int,
        containerHeightPx: Int,
        radiusShadow: Float,
        offsetX: Float,
        offsetY: Float
    ): Path

    /**
     * Sets a circle path by
     *
     * @param radius sets a radius of the circle
     * @param radiusShadow sets offsets for radiusShadow of the view
     *
     * @return [Path] as a result
     */
    fun circle(
        radius: Float,
        radiusShadow: Float
    ) : Path

    /**
     * Returns a path of a shape.
     *
     * @return [Path] object which describes a shape of a view.
     */
    fun getPath(): Path
}