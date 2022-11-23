package ge.space.ui.components.bottomsheet.strategy

import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import ge.space.ui.components.bottomsheet.core.SPBottomSheetFragment

/**
 * Default strategy realization of [SPBottomSheetStrategy].
 * Use it when you don't need to create a container.
 *
 */
class SPEmptyStateStrategy<Data> : SPBottomSheetStrategy<Data> {

    override fun onCreate(
        sheetFragment: SPBottomSheetFragment<*>,
        container: ViewGroup,
        dismissEvent: (Data?) -> Unit
    ) = Unit
}