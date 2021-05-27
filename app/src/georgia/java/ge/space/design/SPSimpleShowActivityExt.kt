package ge.space.design

import android.app.AlertDialog
import ge.space.design.main.SimpleShowCaseActivity

fun SimpleShowCaseActivity.showThemeDialog() {
    AlertDialog.Builder(this)
        .setTitle("Choose theme")
        .setSingleChoiceItems(
            arrayOf("Space Dark","Space Light"),
            preferesManager.getInt(SimpleShowCaseActivity.PREFERENCES_THEME, 0)
        ) { dialog, which ->
            preferesManager.edit()?.apply {
                putInt(SimpleShowCaseActivity.PREFERENCES_THEME, which)
                apply()
            }
            dialog.dismiss()
            recreate()
        }.show()
}