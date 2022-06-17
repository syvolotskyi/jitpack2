package ge.space.ui.components.dropdowns

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**
 * Wrapper which allows pass a dismiss handler action
 *
 * @property onDismissed keeps an action for dismiss handle
 */
@Parcelize
class SPDismissHandler(
    val onDismissed: (() -> Unit?)? = null
): Parcelable