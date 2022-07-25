package ge.space.ui.components.text_fields.input.dropdown

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import androidx.fragment.app.FragmentActivity
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bottomsheet.builder.SPBottomSheetBuilder
import ge.space.ui.components.list_adapter.SPMenuAdapter
import ge.space.ui.components.list_adapter.SPMenuAdapterListener
import ge.space.ui.components.bottomsheet.strategy.SPListSheetStrategy
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnDropdownBind
import ge.space.ui.util.extension.EMPTY_TEXT
import ge.space.ui.util.extension.onClick
import ge.space.ui.util.extension.setHeight
import ge.space.ui.util.extension.setWidth

/**
 * Dropdown view which allows to manipulate next parameters:
 *
 * @property text allows to set text
 * @property bindViewValue sets a bind view lambda
 * @property onClickListener handles click listener
 * @property listItems sets a list of items
 * @property defaultItem sets a default item
 */
class SPTextFieldDropdown<Item> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Dropdown
) : SPTextFieldInput(context, attrs, defStyleAttr, defStyleRes) {

    lateinit var activity: FragmentActivity

    /**
     * Binding an item view after selecting
     */
    var bindViewValue: (view: SPTextFieldDropdown<Item>, item: Item) -> Unit = { _, _ -> }

    /**
     * On dropdown click listener
     */
    var onClickListener: (SPTextFieldDropdown<Item>) -> Unit = { }

    /**
     * Sets items
     */
    var listItems: List<Item> = emptyList()

    /**
     * Sets a default item
     */
    private var defaultItem: Item? = null

    /**
     * An selected item
     */
    private var selectedItem: Item? = null

    /**
     * Sets an adapter for bottom sheet
     */
    private var adapter: SPMenuAdapter<*, Item>? = null

    /**
     * Sets a inflate Type
     */
    private var inflateType: InflateType = InflateType.None

    /**
     * Sets a left image, if inflate type is withImage
     */
    fun setImage(view: View) {
        if (inflateType == InflateType.WithIcon) {
            startView = view
        }
    }


    /**
     * Sets a left image and specify a container size, if inflate type is withImage
     */
    fun setImage(view: View, width: Int, height: Int) {
        if (inflateType == InflateType.WithIcon) {
            binding.flStart.setWidth(width)
            binding.flStart.setHeight(height)

            startView = view
        }
    }

    /**
     * Sets a default Item and bind it
     */
    fun setDefault(type: Item) {
        defaultItem = type

        defaultItem?.let { bindViewValue(this, it) }
    }

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPTextFieldDropdown,
            defStyleAttr,
            defStyleRes
        ) {
            applyStyledAttrs()
        }

        onClick { handleOnClick() }
    }

    override fun handleContentInputView() {
        super.handleContentInputView()

        contentInputView.isFocusableInTouchMode = false
        contentInputView.onClick { handleOnClick() }
    }

    private fun handleOnClick() =
        adapter?.let { adapter ->
            SPBottomSheetBuilder<Item>()
                .setTitle(labelText)
                .setResultListener { result ->  result?.let { bindItem(it) } }
                .setStrategy(SPListSheetStrategy(adapter))
                .build()
                .show(activity)
        } ?: onClickListener(this)

    /**
     * Bind Item
     */
    fun bindItem(item: Item) {

        bindViewValue(this, item)
    }

    /**
     * Returns an selected item if it exists
     */
    fun getSelectedItem() = selectedItem

    override fun setViewStyle(newStyle: Int) {
        super.setViewStyle(newStyle)
        context.withStyledAttributes(newStyle, R.styleable.SPTextFieldDropdown) {
            applyStyledAttrs()
        }
    }

    private fun TypedArray.applyStyledAttrs() {
        val inflateId = getInt(
            R.styleable.SPTextFieldDropdown_inflateType,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
        inflateType = InflateType.values()[inflateId]

    }

    enum class InflateType {
        None,
        WithIcon
    }

    /**
     * Builder class which allows to create [SPTextFieldDropdown]
     */
    companion object
    class SPTextFieldDropdownBuilder<Item>(private val fragmentActivity: FragmentActivity) {
        private var title: String = EMPTY_TEXT
        private var description: String = EMPTY_TEXT
        private var listener: (SPTextFieldDropdown<Item>) -> Unit = { }
        private var default: Item? = null
        private var view: SPTextFieldDropdown<Item>? = null
        private var items: List<Item> = emptyList()
        private var bottomSheetAdapter: SPMenuAdapter<*, Item>? = null
        private var style: Int = R.style.SPTextField_Dropdown
        private var onBind: SPOnDropdownBind<Item>? = null

        /**
         * Sets a style resource
         */
        fun setStyle(@StyleRes newStyle: Int = R.style.SPTextField_Dropdown): SPTextFieldDropdownBuilder<Item> {
            style = newStyle

            return this
        }

        /**
         * Sets a view from xml
         */
        @Suppress("UNCHECKED_CAST")
        fun withView(view: SPTextFieldDropdown<*>): SPTextFieldDropdownBuilder<Item> {
            this.view = view as SPTextFieldDropdown<Item>

            return this
        }

        /**
         * Sets a label text.
         */
        fun setTitle(string: String): SPTextFieldDropdownBuilder<Item> {
            this.title = string

            return this
        }

        /**
         * Sets a description text.
         */
        fun setDescription(string: String): SPTextFieldDropdownBuilder<Item> {
            this.description = string

            return this
        }

        /**
         * Sets a list of item with witch will work dropdown
         */
        fun setItems(items: List<Item>): SPTextFieldDropdownBuilder<Item> {
            this.items = items

            return this
        }

        /**
         * Sets a adapter for bottom sheet
         */
        fun setBottomSheetAdapter(adapter: SPMenuAdapter<*, Item>): SPTextFieldDropdownBuilder<Item> {

            this.bottomSheetAdapter = adapter

            return this
        }

        /**
         * Sets a on click listener for dropdown
         */
        fun setOnClickListener(function: (SPTextFieldDropdown<Item>) -> Unit): SPTextFieldDropdownBuilder<Item> {
            listener = function

            return this
        }

        /**
         * Binding an item view after selecting
         */
        fun setOnBindDropdownItem(onBindInterface: SPOnDropdownBind<Item>): SPTextFieldDropdownBuilder<Item> {
            onBind = onBindInterface

            return this
        }

        /**
         * Sets a default item
         */
        fun setDefault(default: Item): SPTextFieldDropdownBuilder<Item> {
            this.default = default

            return this
        }

        /**
         * Build a new dropdown view or update already created
         *
         * @return an instance of SPTextFieldDropdown<*>
         */
        fun build(context: Context): SPTextFieldDropdown<Item> =
            (view ?: SPTextFieldDropdown(context)).apply {
                setViewStyle(style)
                adapter = bottomSheetAdapter
                adapter?.setAdapterItems(items)
                adapter?.adapterListener = object : SPMenuAdapterListener<Item> {
                    override fun onItemClickListener(position: Int, data: Item?) {
                        data?.let { bindItem(data) }
                    }
                }

                labelText = title
                descriptionText = description
                activity = fragmentActivity
                onClickListener = listener
                onBind?.let { bindViewValue = it.getBindItemModel() }
                listItems = items

                default?.let { setDefault(it) }
            }
    }
}

