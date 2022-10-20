package ge.space.ui.components.tab_switcher

import android.content.Context
import android.content.res.TypedArray
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.core.content.withStyledAttributes
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpTabNavigationLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.components.buttons.SPButton
import ge.space.ui.util.extension.*

/**
 * SPTabNavigation view extended from [SPBaseView] that allows to change its configuration.
 * Max size is 4, list of tabs can be set from xml (use tabs="array/strings") or from code
 *
 */
class SPTabNavigation @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTabNavigation
) : SPBaseView(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpTabNavigationLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var inactiveTextAppearance: Int = 0

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var activeTextAppearance: Int = 0

    private var list = arrayListOf<SPTabData>()
    private var selectedNode: SPTabData? = null
    private var selectedButton: SPButton? = null
    private var onTabChooseListener: ((title: String, tabIndex: Int) -> Unit)? = null

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTabNavigation,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPTabNavigation) {
            withStyledAttributes()
        }
    }

    /**
     * Set tab selected listener
     */
    fun setOnTabSelectedListener(listener: (title: String, tabIndex: Int) -> Unit) {
        onTabChooseListener = listener
    }

    /**
     * Set list of tabs. Max size is 3
     */
    fun setTabs(tabs: List<String>) {
        if (tabs.size > MAX_SIZE) throw IllegalStateException("Max size is $MAX_SIZE")
        tabs.forEachIndexed { index, tabTitle -> addTab(tabTitle, index) }
        if (selectedNode == null) {
            addSelectedTabView()
        }
    }

    private fun addSelectedTabView() {
        selectedButton = getActiveTabView(list[FIRST_TAB].title)
        binding.parent.addView(selectedButton)
        applyNewSelectedConstrains(list[FIRST_TAB].data.id)
    }

    /**
     * Set selected tab by key
     */
    fun setSelectedTab(key: Int) =
        tabClicked(list[key], key)

    private fun addTab(title: String, key: Int) {
        val currentTabView = getInactiveTabView(title)
        val lastTabView = getLastTabItem()

        val currentNode = SPTabData(currentTabView).apply { this.title = title }
        currentTabView.onClick { tabClicked(currentNode, key) }
        binding.parent.addView(currentTabView)
        list.add(currentNode)

        resetConstrainsSet(lastTabView?.data, currentTabView)
    }

    private fun resetConstrainsSet(
        lastTabView: View?,
        currentTabView: TextView
    ) = binding.parent.applyConstrainChanges {
        connectTab(currentTabView, lastTabView)
    }

    private fun ConstraintSet.connectTab(currentTabView: TextView, prevView: View?) {

        prevView?.let {
            connect(
                prevView.id, ConstraintSet.END,
                currentTabView.id, ConstraintSet.START, DEFAULT_INT
            )
        }

        connect(
            currentTabView.id, ConstraintSet.START, prevView?.id ?: PARENT_ID,
            if (prevView == null) ConstraintSet.START else ConstraintSet.END,
            DEFAULT_INT
        )
        connect(currentTabView.id, ConstraintSet.TOP, PARENT_ID, ConstraintSet.TOP, DEFAULT_INT)
        connect(
            currentTabView.id,
            ConstraintSet.BOTTOM,
            PARENT_ID,
            ConstraintSet.BOTTOM,
            DEFAULT_INT
        )
        connect(currentTabView.id, ConstraintSet.END, PARENT_ID, ConstraintSet.END, DEFAULT_INT)
    }

    private fun getLastTabItem() = if (getTabsSize() > 0) list[getTabsSize() - 1] else null

    private fun tabClicked(selectedTab: SPTabData, key: Int) {
        selectedNode = selectedTab
        selectedButton?.text = selectedTab.title
        applyNewSelectedConstrains(selectedTab.data.id)
        binding.parent.post { onTabChooseListener?.invoke(selectedTab.title, key) }
    }

    private fun getInactiveTabView(title: String) =
        TextView(context, null, R.style.SPTabNavigationUnselectedTab).apply {
            layoutParams = getLayoutParamsFromStyle(context, R.style.SPTabNavigationUnselectedTab)
            id = generateViewId()
            text = title
            gravity = Gravity.CENTER
            setTextStyle(inactiveTextAppearance)
        }


    private fun getActiveTabView(title: String) =
        SPButton(context, defStyleRes = R.style.SPSelectedTab).apply {
            layoutParams = getLayoutParamsFromStyle(context, R.style.SPSelectedTab)
            setViewStyle(R.style.SPSelectedTab)
            id = generateViewId()
            text = title
            textAppearance = activeTextAppearance
        }

    private fun getTabsSize() = list.size

    private fun TypedArray.withStyledAttributes() {
        getResourceId(
            R.styleable.SPTabNavigation_activeTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            activeTextAppearance = it
        }

        getResourceId(
            R.styleable.SPTabNavigation_inActiveTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            inactiveTextAppearance = it
        }

        getResourceId(
            R.styleable.SPTabNavigation_tabs,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            setTabs(resources.getStringArray(it).toList())
        }
    }

    private fun applyNewSelectedConstrains(selectedId: Int) {
        binding.parent.applyConstrainChanges {
            selectedButton?.let {
                connect(
                    it.id,
                    ConstraintSet.START,
                    selectedId,
                    ConstraintSet.START,
                    0
                )
                connect(it.id, ConstraintSet.END, selectedId, ConstraintSet.END, 0)
                connect(
                    it.id,
                    ConstraintSet.TOP,
                    PARENT_ID,
                    ConstraintSet.TOP,
                    resources.getDimensionPixelSize(R.dimen.dimen_p_2)
                )
                TransitionManager.beginDelayedTransition(binding.parent)
            }
        }
    }

    companion object {
        const val FIRST_TAB = 0
        const val SECOND_TAB = 1
        const val THIRD_TAB = 2
        private const val MAX_SIZE = 3
    }
}