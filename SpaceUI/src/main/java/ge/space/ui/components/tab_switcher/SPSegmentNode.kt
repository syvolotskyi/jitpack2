package ge.space.ui.components.tab_switcher

import android.view.View

// Constructor to create a new node
// next and prev is by default initialized as null
class SPSegmentNode(var data: View) {

    var prev: SPSegmentNode? = null
    var next: SPSegmentNode? = null
}