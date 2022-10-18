package ge.space.ui.components.tab_switcher

import android.content.Context
import android.content.res.TypedArray
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.core.content.withStyledAttributes
import androidx.core.view.forEachIndexed
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpDividerLayoutBinding
import ge.space.spaceui.databinding.SpSegmentControlLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.*

/**
 * SPSegmentControl view extended from [SPBaseView] that allows to change its configuration.
 * Max size is 4, list of tabs can be set from xml (use tabs="array/strings") or from code
 *
 */
class SPSegmentControl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPSegmentControl
) : SPBaseView(context, attrs, defStyleAttr, defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpSegmentControlLayoutBinding.inflate(LayoutInflater.from(context), this)
    }

    /**
     * Sets a text appearance
     */
    @StyleRes
    private var inactiveTextAppearance: Int = 0

    private var list = arrayListOf<SPSegmentNode>()
    private var selectedNode: SPSegmentNode? = null
    private var onTabChooseListener: (String, Int) -> Unit = { _, _ -> }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPSegmentControl,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPSegmentControl) {
            withStyledAttributes()
        }
    }

    /**
     * Set tab selected listener
     */
    fun setOnTabSelectedListener(listener: (String, Int) -> Unit) {
        onTabChooseListener = listener
    }

    /**
     * Set list of tabs. Max size is 3
     */
    fun setTabs(tabs: List<String>) {
        if (tabs.size > MAX_SIZE) throw IllegalStateException("Max size is $MAX_SIZE")
        tabs.forEachIndexed { index, view ->
            addTab(view, index)
        }
    }

    /**
     * Set selected tab by key
     */
    fun setSelectedTab(key: Int) =
        tabClicked(list[key], key)

    private fun addTab(title: String, key: Int) {
        val currentTabView = getInactiveTabView(title)
        val lastTabView = getLastTabItem()
        val divider = if (lastTabView != null) {
            createDivider()
        } else null
        val currentNode = SPSegmentNode(currentTabView).apply { this.title = title }
        currentTabView.onClick { tabClicked(currentNode, key) }
        divider?.let {
            lastTabView?.nextDivider = it
            currentNode.prevDivider = it
            binding.parent.addView(it)
        }

        binding.parent.addView(currentTabView)
        list.add(currentNode)

        resetConstrainsSet(divider, lastTabView, currentTabView)
        if (selectedNode == null) {
            tabClicked(currentNode, key)
        }
    }

    private fun resetConstrainsSet(
        divider: View?,
        lastTabView: SPSegmentNode?,
        currentTabView: TextView
    ) {
        ConstraintSet().apply {
            clone(binding.parent)
            if (divider != null && lastTabView != null) {
                connectDivider(divider, lastTabView.data, currentTabView)
            }
            connectTab(currentTabView, divider)
            applyTo(binding.parent)
        }
    }

    private fun ConstraintSet.connectTab(currentTabView: TextView, divider: View?) {
        connect(
            currentTabView.id, ConstraintSet.START, divider?.id ?: PARENT_ID,
            if (divider == null) ConstraintSet.START else ConstraintSet.END,
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

    private fun ConstraintSet.connectDivider(divider: View, lastItem: View, tabView: TextView) {
        connect(divider.id, ConstraintSet.START, lastItem.id, ConstraintSet.END, DEFAULT_INT)
        connect(divider.id, ConstraintSet.END, tabView.id, ConstraintSet.START, DEFAULT_INT)
        connect(divider.id, ConstraintSet.TOP, PARENT_ID, ConstraintSet.TOP, DEFAULT_INT)
        connect(divider.id, ConstraintSet.BOTTOM, PARENT_ID, ConstraintSet.BOTTOM, DEFAULT_INT)
        connect(lastItem.id, ConstraintSet.END, divider.id, ConstraintSet.START, DEFAULT_INT)
    }

    private fun getLastTabItem() = if (getTabsSize() > 0) list[getTabsSize() - 1] else null

    private fun tabClicked(selectedTab: SPSegmentNode, key: Int) {
        selectedNode?.prevDivider?.show()
        selectedNode?.nextDivider?.show()
        selectedTab.prevDivider?.hide()
        selectedTab.nextDivider?.hide()
        selectedNode = selectedTab
        binding.selectedItem.text = selectedTab.title
        applyNewSelectedConstrains(selectedTab.data.id)
        binding.parent.post { onTabChooseListener(selectedTab.title, key) }
    }

    private fun getInactiveTabView(title: String) =
        TextView(context, null, R.style.SPSegmentControlUnselected).apply {
            id = generateViewId()
            text = title
            gravity = Gravity.CENTER
            setTextStyle(inactiveTextAppearance)
            layoutParams = getLayoutParamsFromStyle(context, R.style.SPSegmentControlUnselected)
        }


    private fun createDivider(): View =
        SpDividerLayoutBinding.inflate(LayoutInflater.from(context)).root
            .apply {
                id = View.generateViewId()
                layoutParams = getLayoutParamsFromStyle(context, R.style.SPSegmentControlDivider)
            }

    private fun getTabsSize() = list.size

    private fun TypedArray.withStyledAttributes() {

        getResourceId(
            R.styleable.SPSegmentControl_activeTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            binding.selectedItem.textAppearance = it
        }

        getResourceId(
            R.styleable.SPSegmentControl_inActiveTextAppearance,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            inactiveTextAppearance = it
        }

        getResourceId(
            R.styleable.SPSegmentControl_tabs,
            DEFAULT_OBTAIN_VAL
        ).handleAttributeAction(DEFAULT_OBTAIN_VAL) {
            setTabs(resources.getStringArray(it).toList())
        }
    }

    private fun applyNewSelectedConstrains(selectedId: Int) {
        ConstraintSet().apply {
            clone(binding.parent)
            connect(
                binding.selectedItem.id,
                ConstraintSet.START,
                selectedId,
                ConstraintSet.START,
                0
            )
            connect(binding.selectedItem.id, ConstraintSet.END, selectedId, ConstraintSet.END, 0)
            TransitionManager.beginDelayedTransition(binding.parent)
            applyTo(binding.parent)
        }
    }

    companion object {
        const val FIRST_TAB = 0
        const val SECOND_TAB = 1
        const val THIRD_TAB = 2
        private const val MAX_SIZE = 3
    }
}