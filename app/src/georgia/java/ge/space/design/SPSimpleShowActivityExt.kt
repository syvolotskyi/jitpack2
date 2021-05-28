package ge.space.design

import androidx.appcompat.app.AlertDialog
import ge.space.design.main.ui.SPBaseActivity


fun SPBaseActivity.showThemeDialog() {
    AlertDialog.Builder(this)
        .setTitle("Choose theme")
        .setSingleChoiceItems(
            arrayOf("Space Dark","Space Light"),
            preferesManager.getInt(SPBaseActivity.PREFERENCES_THEME, 0)
        ) { dialog, which ->
            preferesManager.edit()?.apply {
                putInt(SPBaseActivity.PREFERENCES_THEME, which)
                apply()
            }
            dialog.dismiss()
            recreate()
        }.show()
}