package ge.space.ui.components.text_fields.input.dropdown

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import ge.space.extensions.EMPTY_TEXT
import ge.space.spaceui.R
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.text_fields.input.base.SPEndViewType
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.base.setupEndViewByType
import ge.space.ui.components.text_fields.input.dropdown.data.SPOnBindInterface
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView

/**
 * Dropdown view which allows to manipulate next parameters:
 *
 * @property text allows to set text
 * @property bindViewValue sets a bind view lambda
 * @property onClickListener handles click listener
 * @property listItems sets a list of items
 * @property defaultItem sets a default item
 */
class SPTextFieldDropdown<T> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPTextField_Dropdown
) : SPTextFieldInput(context, attrs, defStyleAttr, defStyleRes) {

    /**
     * Binding a item view after selecting
     */
    var bindViewValue: (view: SPTextFieldDropdown<T>, item: T) -> Unit = { _, _ -> }

    /**
     * On dropdown click listener
     */
    var onClickListener: (SPTextFieldDropdown<T>) -> Unit = { }

    /**
     * Sets items
     */
    var listItems: List<T> = emptyList()

    /**
     * Sets a default item
     */
    private var defaultItem: T? = null

    /**
     * Sets a inflate Type
     */
    private var inflateType: InflateType = InflateType.None


    /**
     * Sets a left image, if inflate type is withImage
     */
    fun setImage(view: View) {
        if (inflateType == InflateType.WithIcon) {
            view.setHeight(context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small))
            view.setWidth(context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small))
            startView = view
        }
    }

    /**
     * Sets a default Item and bind it
     */
    fun setDefault(type: T) {
        defaultItem = type

        defaultItem?.let { bindViewValue(this, it) }
    }

    /**
     * Bind selected Item
     */
    fun onSelectedItem(item: T) =
        bindViewValue(this, item)

    override fun setViewStyle(newStyle: Int) {
        super.setViewStyle(newStyle)
        val styleAttrs =
            context.theme.obtainStyledAttributes(newStyle, R.styleable.SPTextFieldDropdown)

        styleAttrs.run {
            val inflateId = getInt(
                R.styleable.SPTextFieldDropdown_inflateType,
                SPBaseView.DEFAULT_OBTAIN_VAL
            )
            inflateType = InflateType.values()[inflateId]

            contentInputView = SPViewData.SPTextData(
                text,
                R.style.h700_bold_caps_text_field,
                SPViewData.SPViewDataParams(gravity = Gravity.START),
                null
            ).createView(context) as TextView

            setupEndViewByType(SPEndViewType.IMAGE, R.drawable.ic_chevron_down_24_regular)
        }

        setOnClickListener { onClickListener(this) }
    }

    enum class InflateType {
        None,
        WithIcon
    }

    /**
     * Builder class which allows to create [SPTextFieldDropdown]
     */
    companion object
    class SPTextFieldDropdownBuilder<T> {
        private var title: String = EMPTY_TEXT
        private var description: String = EMPTY_TEXT
        private var listener: (SPTextFieldDropdown<T>) -> Unit = { }
        private var default: T? = null
        private var view: SPTextFieldDropdown<T>? = null
        private var items: List<T> = emptyList()
        private var style: Int = R.style.SPTextField_Dropdown
        private var onBind: SPOnBindInterface<T>? = null

        /**
         * Sets a style resource
         */
        fun setStyle(@StyleRes newStyle: Int = R.style.SPTextField_Dropdown): SPTextFieldDropdownBuilder<T> {
            style = newStyle

            return this
        }

        /**
         * Sets a view from xml
         */
        @Suppress("UNCHECKED_CAST")
        fun withView(view: SPTextFieldDropdown<*>): SPTextFieldDropdownBuilder<T> {
            this.view = view as SPTextFieldDropdown<T>

            return this
        }

        /**
         * Sets a label text.
         */
        fun setTitle(string: String): SPTextFieldDropdownBuilder<T> {
            this.title = string

            return this
        }

        /**
         * Sets a description text.
         */
        fun setDescription(string: String): SPTextFieldDropdownBuilder<T> {
            this.description = string

            return this
        }

        /**
         * Sets a list of item with witch will work dropdown
         */
        fun setItems(items: List<T>): SPTextFieldDropdownBuilder<T> {
            this.items = items

            return this
        }

        /**
         * Sets a on click listener for dropdown
         */
        fun setOnClickListener(function: (SPTextFieldDropdown<T>) -> Unit): SPTextFieldDropdownBuilder<T> {
            listener = function

            return this
        }

        /**
         * Binding a item view after selecting
         */
        fun setOnBindItem(onBindInterface: SPOnBindInterface<T>): SPTextFieldDropdownBuilder<T> {
            onBind = onBindInterface

            return this
        }

        /**
         * Sets a default item
         */
        fun setDefault(default: T): SPTextFieldDropdownBuilder<T> {
            this.default = default

            return this
        }

        /**
         * Build a new dropdown view or update already created
         *
         * @return an instance of SPTextFieldDropdown<*>
         */
        fun build(context: Context): SPTextFieldDropdown<*> =
            (view ?: SPTextFieldDropdown(context)).apply {
                setViewStyle(style)

                labelText = title
                descriptionText = description

                onClickListener = listener
                onBind?.let { bindViewValue = it.getBindItemModel() }
                listItems = items

                default?.let { setDefault(it) }
            }
    }
}

