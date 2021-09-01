package ge.space.design.main.util

import androidx.appcompat.app.AlertDialog
import ge.space.design.main.ui.SPBaseActivity

fun SPBaseActivity.showThemeDialog() {
    AlertDialog.Builder(this)
        .setTitle("Choose theme")
        .setSingleChoiceItems(
            arrayOf("Space Dark","Space Light"),
            preferencesManager.getInt(SPBaseActivity.PREFERENCES_THEME, 0)
        ) { dialog, which ->
            preferencesManager.edit()?.apply {
                putInt(SPBaseActivity.PREFERENCES_THEME, which)
                apply()
            }
            dialog.dismiss()
            recreate()
        }.show()
}