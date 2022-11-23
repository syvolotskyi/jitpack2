package ge.space.ui.components.bottomsheet.navigation

import androidx.fragment.app.*
import ge.space.spaceui.R

open class SPBottomSheetNavigator @JvmOverloads constructor(
    private val containerId: Int,
    private val fragmentManager: FragmentManager,
    private val fragmentFactory: FragmentFactory = fragmentManager.fragmentFactory,
    private val dismissEvent: (Any?) -> Unit
) : SPNavigator {

    private val localStackCopy = mutableListOf<String>()

    /**
     * Perform transition described by the navigation command
     *
     * @param command the navigation command to apply
     */
    override fun applyCommand(command: SPCommand) {
        fragmentManager.executePendingTransactions()
        when (command) {
            is SPOpenScreen -> commitNewFragmentScreen(command.screen)
            is SPBackTo -> backTo(command)
            is SPBack -> back()
        }
    }

    /**
     * If localStackCopy is not empty than popBackStack, if not than call dismissEvent with null result
     */
    private fun back() {
        if (localStackCopy.isNotEmpty()) {
            fragmentManager.popBackStack()
            localStackCopy.removeAt(localStackCopy.lastIndex)
        } else {
            dismissEvent(null)
        }
    }

    /**
     * If localStackCopy is not empty than use replace transaction for a fragment and add it to back stack,
     * if not than use add transaction
     *
     * @param screen [SPBottomSheetScreen]  is a screen contain fragment to be showed
     */
    private fun commitNewFragmentScreen(screen: SPBottomSheetScreen) {
        val fragment = screen.createFragment(fragmentFactory)

        with(fragmentManager.beginTransaction()) {
            if (localStackCopy.isNotEmpty()) {
                setCustomAnimations(
                    R.anim.bottom_menu_dialog_enter_from_right,
                    R.anim.bottom_menu_dialog_fade_out,
                    R.anim.bottom_menu_dialog_fade_in,
                    R.anim.bottom_menu_dialog_exit_to_right
                ).replace(containerId, fragment, screen.screenKey)

                addToBackStack(screen.screenKey)
                localStackCopy.add(screen.screenKey)
                commit()
            } else {
                localStackCopy.add(screen.screenKey)
                add(containerId, fragment, screen.screenKey).commitNow()
            }
        }
    }

    /**
     * Performs [BackTo] command transition
     */
    private fun backTo(command: SPBackTo) {
        if (command.screen == null) {
            backToRoot()
        } else {
            val screenKey = command.screen.screenKey
            val index = localStackCopy.indexOfFirst { it == screenKey }
            if (index != -1) {
                val forRemove = localStackCopy.subList(index, localStackCopy.size)
                fragmentManager.popBackStack(forRemove.first().toString(), 0)
                forRemove.clear()
            } else {
                backToRoot()
            }
        }
    }

    /**
     * Clear localStackCopy and back to first screen in a stack
     */
    private fun backToRoot() {
        localStackCopy.clear()
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}