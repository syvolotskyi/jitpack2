package ge.space.design.main

import android.app.Dialog
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment

interface SPShowCaseDisplay {
    fun show(intent: Intent)
    fun show(fragment: Fragment)
    fun show(dialog: Dialog)
    fun show(view: View)
    fun show(launchAction: SPLaunchAction)
}