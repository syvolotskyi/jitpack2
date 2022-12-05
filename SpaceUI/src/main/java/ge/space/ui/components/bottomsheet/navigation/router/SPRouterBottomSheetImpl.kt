package ge.space.ui.components.bottomsheet.navigation.router

import android.os.Handler
import android.os.Looper
import ge.space.ui.components.bottomsheet.navigation.*
import ge.space.ui.components.bottomsheet.strategy.SPFragmentSheetStrategy.Companion.DISMISS_KEY

/**
 * [SPRouterBottomSheetImpl] is implementation of [SPBottomSheetRouter]
 *
 * @param navigator [SPNavigator] is implementation of navigation for bottom sheet
 */
class SPRouterBottomSheetImpl(var navigator: SPNavigator?) : SPBottomSheetRouter {
    private val mainHandler = Handler(Looper.getMainLooper())
    private val resultWire = SPResultWire()

    /**
     * Passes `commands` to the [Navigator] if it available.
     * Else puts it to the pending commands queue to pass it later.
     * @param commands navigation command array
     */
    protected fun executeCommands(commands: SPCommand) {
        mainHandler.post {
            navigator?.applyCommand(commands)
        }
    }

    /**
     * Sets data listener with given key
     *
     * After first call listener will be removed.
     * @param key [String] as a default we use [DISMISS_KEY]
     * @param listener [SPBottomSheetResultListener]
     */
    override fun setResultListener(
        key: String,
        listener: SPBottomSheetResultListener
    ) {
        resultWire.setResultListener(key, listener)
    }

    /**
     * Remove Navigator to avoid memory leak
     */
    override fun removeNavigator() {
        navigator = null
    }

    /**
     * Sends data to listener with given key.
     */
    override fun sendResult(key: String, data: Any?) {
        resultWire.sendResult(key, data)
    }

    /**
     * Replace current screen.
     *
     * @param screen [SPBottomSheetScreen] is screen which contains fragment
     */
    override fun openScreen(screen: SPBottomSheetScreen) {
        executeCommands(SPOpenScreen(screen))
    }

    /**
     * Return fragmentBack to the needed screen from the chain.
     *
     * Behavior in the case when no needed screens found depends on
     * the processing of the [BackTo] command in a [Navigator] implementation.
     *
     * @param screen screen
     */
    override fun backTo(screen: SPBottomSheetScreen?) {
        executeCommands(SPBackTo(screen))
    }

    /**
     * Return to the previous screen in the chain.
     *
     * Behavior in the case when the current screen is the root depends on
     * the processing of the [Back] command in a [Navigator] implementation.
     */
    override fun exit() {
        executeCommands(SPBack())
    }
}