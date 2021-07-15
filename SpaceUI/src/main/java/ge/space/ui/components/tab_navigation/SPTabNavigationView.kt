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
        showCurrentTabButton(navigationModel)
        previewNavigationModel = navigationModel
    }

    /**
    * show current Tab navigation
    */
    private fun showCurrentTabButton(tab: SPTabNavigationModel) {
        forEach {
            (it as SPTabNavigationChildView).isActive = (it.tag == tab)
        }
    }

    /**
     * set child view items
     */
    var items: MutableList<SPTabNavigationModel> ? = null


    /**
     * add child views into container
     */
    fun setUp(clickListener: (SPTabNavigationModel) -> Unit) {
        items?.forEachIndexed { index, navigationItem ->
            val childView = SPTabNavigationChildView(context)
            childView.setParametersToChildView(navigationItem)
            childView.setStyle(navigationItem.style)
            childView.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.MATCH_PARENT
            ).apply {
                if (index != ZERO && index != items!!.size - ONE || items!!.size == TWO)
                    weight = ONE_WEIGHT
            }

            childView.setOnClickListener { view ->
                (view.tag as? SPTabNavigationModel)?.let { tab ->
                    if (previewNavigationModel != null && previewNavigationModel != tab){
                        clickListener.invoke(navigationItem)
                        changeNavigationTab(tab)
                    }

                }
            }

            changeNavigationTab(items!!.first())
            addView(childView)
        }

    }

    /**
     * sets parameters to child View
     * @property id [Int]  generated ID value.
     * @property tag [SPTabNavigationModel] view tag.
     * @property navigationItem [SPTabNavigationModel] set SPTabNavigationModel model to ChildView.
     */
    private fun SPTabNavigationChildView.setParametersToChildView(navigationItem: SPTabNavigationModel) {
        this.id = generateViewId()
        this.tag = navigationItem
        this.navigationItem = navigationItem
    }

    companion object {
        const val ZERO = 0
        const val ONE = 1
        const val TWO = 2
        const val ONE_WEIGHT = 1f
    }

}

