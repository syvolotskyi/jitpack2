package ge.space.design

import android.app.AlertDialog
import ge.space.design.main.SPSPShowCaseActivity

fun SPSPShowCaseActivity.showThemeDialog() {
    AlertDialog.Builder(this)
        .setTitle("Choose theme")
        .setSingleChoiceItems(
            arrayOf("Space Dark","Space Light"),
            preferesManager.getInt(SPSPShowCaseActivity.PREFERENCES_THEME, 0)
        ) { dialog, which ->
            preferesManager.edit()?.apply {
                putInt(SPSPShowCaseActivity.PREFERENCES_THEME, which)
                apply()
            }
            dialog.dismiss()
            recreate()
        }.show()
}