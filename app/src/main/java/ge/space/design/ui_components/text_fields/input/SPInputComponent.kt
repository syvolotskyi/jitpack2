package ge.space.design.ui_components.text_fields.input

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemListTextFieldBinding
import com.example.spacedesignsystem.databinding.SpLayoutTextFieldsListShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.extensions.EMPTY_TEXT
import ge.space.ui.components.text_fields.input.base.*
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged

class SPInputComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.text_input

    override fun getDescriptionResId(): Int = R.string.text_input_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTextFieldsListShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            SPTextFieldsInputButtonStyles.list.onEach { buttonSample ->

                val resId = buttonSample.resId
                val itemBinding = SpItemListTextFieldBinding.inflate(
                    environment.requireLayoutInflater(),
                    layoutBinding.fieldsLayout,
                    true
                )
                val context = itemBinding.simpleInput.context

                with(itemBinding.simpleInput) {
                    setViewStyle(buttonSample.resId)
                    setupInputTextWithDone(this, environment.context)
                    doOnTextChanged { text, _, _, _ ->
                        isDistractive = false
                        if (text.toString() == TEXT_WATCHER_CHECK_TEXT) {
                            showToast(context, text.toString())
                        }
                    }
                }

                with(itemBinding.buttonName) {
                    val resName = resources.getResourceEntryName(resId)
                    text = resName.substringAfter(DOT, resName)
                }

                itemBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.simpleInput.inputMandatory = isChecked
                }

                itemBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.simpleInput.isEnabled = !isChecked
                }

                itemBinding.cbEnableDescription.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.simpleInput.descriptionText = if (isChecked) {
                        itemBinding.simpleInput.resources.getString(R.string.description)
                    } else {
                        EMPTY_TEXT
                    }
                }
                itemBinding.cbDistractive.setOnCheckedChangeListener { _, isChecked ->
                    itemBinding.simpleInput.isDistractive = isChecked
                }


                itemBinding.rgContent.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.phoneMask ->
                            itemBinding.simpleInput.setupContextViewByType(
                                SPContextViewType.MASK,
                                context.getString(R.string.phone_mask),
                                context.getString(R.string.enter_you_details_here)
                            )
                        R.id.dateMask ->
                            itemBinding.simpleInput.setupContextViewByType(
                                SPContextViewType.MASK,
                                context.getString(R.string.day_mask),
                                context.getString(R.string.enter_you_details_here)
                            )
                        R.id.numberInput ->
                            itemBinding.simpleInput.setupContextViewByType(
                                SPContextViewType.NUMBER,
                                inputHint = context.getString(R.string.enter_amount)
                            )
                        else -> itemBinding.simpleInput.setupContextViewByType(
                            SPContextViewType.TEXT,
                            inputHint = context.getString(R.string.enter_you_details_here)
                        )
                    }
                }

                itemBinding.rgLeading.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.primaryChip -> itemBinding.simpleInput.setupLeadingViewByType(
                            SPLeadingViewType.CARD
                        )
                        R.id.phone -> itemBinding.simpleInput.setupLeadingViewByType(
                            SPLeadingViewType.PHONE_PREFIX,
                            phonePrefix = context.getString(R.string.phone_prefix)
                        )
                        R.id.image -> itemBinding.simpleInput.setupLeadingViewByType(
                            SPLeadingViewType.IMAGE, icon = R.drawable.ic_chat_message_24_regular
                        )

                        R.id.none -> itemBinding.simpleInput.setupLeadingViewByType(
                            SPLeadingViewType.NONE
                        )
                    }
                }

                itemBinding.rgTrail.setOnCheckedChangeListener { _, checkedId ->
                    when (checkedId) {
                        R.id.trailPrimaryChip -> itemBinding.simpleInput.setupTrailViewByType(
                            SPTrailViewType.CARD
                        )
                        R.id.rbCurrency -> itemBinding.simpleInput.setupTrailViewByType(
                            SPTrailViewType.CURRENCY,
                            currency = "$"
                        )
                        R.id.removeIcon -> itemBinding.simpleInput.setupTrailViewByType(
                            SPTrailViewType.REMOVABLE
                        )
                        R.id.noneTrail -> itemBinding.simpleInput.setupLeadingViewByType(
                        SPLeadingViewType.NONE
                    )
                    }
                }


                layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                    with(itemBinding.simpleInput) {
                        labelText = text.toString()
                        descriptionText = text.toString()
                    }
                }

            }
            return layoutBinding.root

        }

        private fun setupInputTextWithDone(textInput: SPTextFieldInput, context: Context) {
            textInput.setOnEditorActionListener(
                TextView.OnEditorActionListener
                { _: TextView?, actionId: Int, event: KeyEvent? ->
                    if (actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event?.action == KeyEvent.ACTION_DOWN
                    ) {
                        showToast(context, "$ACTION_DONE ${textInput.text}")
                        return@OnEditorActionListener true
                    } else if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_FLAG_NAVIGATE_NEXT) {
                        showToast(context, "$ACTION_NEXT ${textInput.text}")

                    }
                    return@OnEditorActionListener false
                })
        }

        private fun showToast(context: Context, text: String) {
            Toast.makeText(
                context,
                text,
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    companion object {
        const val TEXT_WATCHER_CHECK_TEXT = "Space"
        private const val ACTION_NEXT = "Action Next:"
        private const val ACTION_DONE = "Action Done:"
        private const val DOT = "."
    }
}