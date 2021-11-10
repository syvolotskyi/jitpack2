package ge.space.design.main.util

import androidx.appcompat.app.AlertDialog
import com.example.spacedesignsystem.R
import ge.space.design.main.ui.SPBaseActivity
import ge.space.ui.components.dialogs.data.SPDialogIcon
import ge.space.ui.components.dialogs.data.SPDialogInfo
import ge.space.ui.components.dialogs.data.SPDialogInfoHolder
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.dialogs.showMultipleButtonDialog

fun SPBaseActivity.showThemeAlertDialog() {
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

private enum class AppTheme {
    DARK,
    LIGHT
}

fun SPBaseActivity.showThemeCustomDialog() {
    fun updateTheme(theme: AppTheme) {
        preferencesManager.edit().apply {
            putInt(SPBaseActivity.PREFERENCES_THEME, theme.ordinal)
            apply()
        }
        recreate()
    }
    showMultipleButtonDialog(
        SPDialogInfo(
            "Choose theme",
            "",
            arrayListOf(
                SPDialogInfoHolder(
                    "Dark",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    updateTheme(AppTheme.DARK)
                },
                SPDialogInfoHolder(
                    "Light",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    updateTheme(AppTheme.LIGHT)
                }
            )
        ), SPDialogIcon.Alert(R.attr.accent_magenta))
}