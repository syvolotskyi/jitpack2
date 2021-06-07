package ge.space.extensions

import android.view.View
import androidx.constraintlayout.widget.Group

fun Group.setAllOnClickListener(listener: (view: View) -> Unit){
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener(listener)
    }
}