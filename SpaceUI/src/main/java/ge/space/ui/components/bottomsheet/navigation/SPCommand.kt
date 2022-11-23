package ge.space.ui.components.bottomsheet.navigation

/**
 * Navigation command describes screens transition.
 *
 * That can be processed by [Navigator]
 */
interface SPCommand

/**
 * Replaces the current screen.
 */
data class SPOpenScreen(val screen: SPBottomSheetScreen) : SPCommand

/**
 * Rolls fragmentBack the last transition from the screens chain.
 */
class SPBack : SPCommand

/**
 * Rolls fragmentBack to the needed screen from the screens chain.
 *
 * Behavior in the case when no needed screens found depends on an implementation of the [com.github.terrakok.cicerone.Navigator]
 * But the recommended behavior is to return to the root.
 */
data class SPBackTo(val screen: SPBottomSheetScreen?) : SPCommand
