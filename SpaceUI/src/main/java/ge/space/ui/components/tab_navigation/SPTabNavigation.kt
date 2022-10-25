package ge.space.ui.components.tab_navigation

import android.content.Context
import android.content.res.TypedArray
import android.transition.ChangeBounds
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
import androidx.core.transition.addListener
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
     * Sets an inactive tab text appearance
     */
    @StyleRes
    private var inactiveTextAppearance: Int = 0

    /**
     * Sets an active tab text appearance
     */
    @StyleRes
    private var activeTextAppearance: Int = 0

    /**
     * Store list of all inactive tabs
     */
    private var inactiveTabs = arrayListOf<SPTabNavigationData>()

    /**
     * Last selected tab
     */
    private var selectedTabData: SPTabNavigationData? = null


    /**
     * View of the selected tab
     */
    private var selectedTabView: SPButton? = null

    /**
     * Listener allows observing switching tabs
     */
    private var onTabChooseListener: ((title: String, tab: SPNavigationTabs) -> Unit)? = null

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
    fun setOnTabSelectedListener(listener: (title: String, tab: SPNavigationTabs) -> Unit) {
        onTabChooseListener = listener
    }

    /**
     * Set list of tabs. Max size is 3
     */
    fun setTabs(tabs: List<String>) {
        if (tabs.size > MAX_SIZE) throw IllegalStateException("Max size is $MAX_SIZE")
         tabs.forEachIndexed(::addTab)
        if (selectedTabData == null) {
            addSelectedTabView()
        }
    }

    /**
     * Set selected tab by key
     */
    fun setSelectedTab(key: SPNavigationTabs) =
        tabClicked(inactiveTabs[key.tabIndex], key)


    private fun addSelectedTabView() {
        selectedTabView = getActiveTabView(inactiveTabs[SPNavigationTabs.FIRST_TAB.tabIndex].title)
        binding.parent.addView(selectedTabView)
        applyNewSelectedConstrains(inactiveTabs[SPNavigationTabs.FIRST_TAB.tabIndex].tabView.id)
    }

    private fun addTab(key: Int, title: String,) {
        val currentTabView = getInactiveTabView(title)
        val lastTabView = getLastTabItem()

        val currentTabData = SPTabNavigationData(currentTabView, title)
        currentTabView.onClick { tabClicked(currentTabData, getTabByIndex(key)) }
        binding.parent.addView(currentTabView)
        inactiveTabs.add(currentTabData)

        resetConstrainsSet(lastTabView?.tabView, currentTabView)
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

    private fun getLastTabItem() = if (getTabsSize() > 0) inactiveTabs[getTabsSize() - 1] else null

    private fun tabClicked(selectedTab: SPTabNavigationData, key: SPNavigationTabs) {
        selectedTabData = selectedTab
        selectedTabView?.text = selectedTab.title
        applyNewSelectedConstrains(selectedTab.tabView.id) {
            binding.parent.post { onTabChooseListener?.invoke(selectedTab.title, key) }
        }
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

    private fun getTabsSize() = inactiveTabs.size

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

    private fun applyNewSelectedConstrains(selectedId: Int, onEndAnimationListener: () -> Unit = {}) {
        binding.parent.applyConstrainChanges {
            selectedTabView?.let {
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
                val transition = ChangeBounds().apply { addListener(onEnd = { onEndAnimationListener()}) }
                TransitionManager.beginDelayedTransition(binding.parent, transition)
            }
        }
    }

    private fun getTabByIndex(index :Int) = SPNavigationTabs.values().find { it.tabIndex == index } ?: throw java.lang.IllegalStateException()

    /**
     * Enum class store tab indexes for SPTabNavigation
     */
    enum class SPNavigationTabs(val tabIndex: Int) {
        FIRST_TAB(0),
        SECOND_TAB(1),

        /**
         * Optional tab
         */
        THIRD_TAB(2);
    }

    companion object {
        private const val MAX_SIZE = 3
    }
}