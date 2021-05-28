package ge.space.design

import android.app.AlertDialog
import ge.space.design.main.ui.SPShowCaseActivity

fun SPShowCaseActivity.showThemeDialog() {
    AlertDialog.Builder(this)
        .setTitle("Choose theme")
        .setSingleChoiceItems(
            arrayOf("Space Dark","Space Light"),
            preferesManager.getInt(SPShowCaseActivity.PREFERENCES_THEME, 0)
        ) { dialog, which ->
            preferesManager.edit()?.apply {
                putInt(SPShowCaseActivity.PREFERENCES_THEME, which)
                apply()
            }
            dialog.dismiss()
            recreate()
        }.show()
}