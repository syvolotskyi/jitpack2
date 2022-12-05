package ge.space.ui.components.bottomsheet.navigation.router

import ge.space.ui.components.bottomsheet.navigation.*
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy.Companion.DISMISS_KEY

/**
 * Interface [SPBottomSheetRouter] is a helper to work with [SPBottomSheetNavigator]
 * and sending and listening setScreenResult
 */
interface SPBottomSheetRouter {

    /**
     * Sends data to listener with given key.
     */
    fun sendResult(key: String, data: Any? = null)

    /**
     * Sets data listener with given key
     *
     * After first call listener will be removed.
     * @param key [String] as a default we use [DISMISS_KEY]
     * @param listener [SPBottomSheetResultListener]
     */
    fun setResultListener(
        key: String = DISMISS_KEY,
        listener: SPBottomSheetResultListener
    )

    /**
     * Replace current screen.
     *
     * @param screen [SPBottomSheetScreen] is screen which contains fragment
     */
    fun openScreen(screen: SPBottomSheetScreen)

    /**
     * Return fragmentBack to the needed screen from the chain.
     *
     * Behavior in the case when no needed screens found depends on
     * the processing of the [BackTo] command in a [Navigator] implementation.
     *
     * @param screen screen
     */
    fun backTo(screen: SPBottomSheetScreen?)

    /**
     * Return to the previous screen in the chain.
     *
     * Behavior in the case when the current screen is the root depends on
     * the processing of the [Back] command in a [Navigator] implementation.
     */
    fun exit()

    /**
     * Remove Navigator to avoid memory leak
     */
    fun removeNavigator()
}