package ge.space.ui.components.bottomsheet.strategy

import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment
import ge.space.ui.components.bottomsheet.navigation.SPBottomSheetNavigator
import ge.space.ui.components.bottomsheet.navigation.SPBottomSheetScreen
import ge.space.ui.components.bottomsheet.navigation.router.SPBottomSheetRouter
import ge.space.ui.components.bottomsheet.navigation.router.SPRouterBottomSheetImpl
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * Fragment strategy realization of [SPBottomSheetStrategy]
 * Data is onResult return type
 *
 * @param fragment [Fragment] which should be inflated in container
 */

class SPFragmentSheetStrategy<Data>(
    private val fragment: Fragment
) : SPBottomSheetStrategy<Data> {

    private var router: SPBottomSheetRouter? = null
    private val module: Module by lazy { module { single { router!! } } }

    /**
     * Calls for initializing strategy
     *
     * @param sheetFragment [SPBottomSheetFragment<*>] is a bottom sheet fragment
     * @param container [ViewGroup] is parent container
     * @param dismissEvent [() -> Unit)] calls when dialog is dismissed
     */
    override fun onCreate(
        sheetFragment: SPBottomSheetFragment<*>,
        container: ViewGroup,
        dismissEvent: (Data?) -> Unit
    ) {
        super.onCreate(sheetFragment, container, dismissEvent)
        router = SPRouterBottomSheetImpl(
            SPBottomSheetNavigator(
                container.id,
                sheetFragment.childFragmentManager,
                dismissEvent = dismissEvent as (Any?) -> Unit
            )
        )

        loadKoinModules(module)

        router?.setResultListener(DISMISS_KEY) { data -> dismissEvent(data) }
        router?.openScreen(SPBottomSheetScreen { fragment })
    }

    /**
     * On destroy of bottom sheet fragment unloan SPBottomSheetRouter koin module
     * and remove navigator form router to avoid memory leaks
     */
    fun onDestroy() {
        unloadKoinModules(module)
        router?.removeNavigator()
    }

    companion object {
        const val DISMISS_KEY = "KEY_DISMISS"
    }
}