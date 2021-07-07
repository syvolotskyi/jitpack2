package ge.space.ui.components.tab_navigation

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.core.view.forEach
import ge.space.ui.components.tab_navigation.data.SPTabNavigationModel
import ge.space.ui.components.tab_navigation.view.SPTabNavigationChildView

class SPTabNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    /**
     * Set a preview Navigation model to check tab position
     */
    private var previewNavigationModel: SPTabNavigationModel? = null

    /**
     * change tab position per navigation model
     */
    private fun changeNavigationTab(navigationModel: SPTabNavigationModel){
        showCurrentTabNavigation(navigationModel)
        previewNavigationModel = navigationModel
    }

    /**
     * show current Tab navigation
     */
    private fun showCurrentTabNavigation(tab: SPTabNavigationModel) {
        forEach {
            (it as SPTabNavigationChildView).isActive = (it.tag == tab)
        }
    }

    /**
     * set up tab navigation
     * add child view per items list
     * we should add id and tag each childView to check position in list
     * we should add layoutParams each childView to be compatible full screen width
     */

    fun setUp(items: MutableList<SPTabNavigationModel>, clickListener: (SPTabNavigationModel) -> Unit) {
        items.forEachIndexed { index, navigationItem ->
            val childView = SPTabNavigationChildView(context)
            childView.id = generateViewId()
            childView.tag = navigationItem
            childView.navigationItem = navigationItem
            childView.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
            ).apply {
                if (index != 0 && index != items.size - 1 || items.size == 2)
                    weight = 1f
            }

            childView.setOnClickListener { view ->
                (view.tag as? SPTabNavigationModel)?.let { tab ->
                    if (previewNavigationModel != null && previewNavigationModel != tab){
                        clickListener.invoke(navigationItem)
                        changeNavigationTab(tab)
                    }

                }
            }
            changeNavigationTab(items.first())
            addView(childView)
        }

    }

}

