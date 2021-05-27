package ge.space.design.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import java.io.Serializable



interface ShowCaseComponent : Serializable {

    @StringRes
    fun getNameResId(): Int

    @StringRes
    fun getDescriptionResId(): Int = NO_RES_ID

    fun getDescriptionFormatArgs(): Array<Any> = arrayOf()

    @DrawableRes
    fun getIconResId(): Int = NO_RES_ID

    /**
     * The following types are supported:
     * Activity.class,
     * Fragment.class (DialogFragment.class),
     * Dialog.class,
     * View.class,
     * ComponentFactory.class,
     */
    fun getComponentClass(): Class<*>? = null

    fun getSubComponents(): List<ShowCaseComponent> = listOf()
}
