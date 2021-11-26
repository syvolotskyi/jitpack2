package ge.space.design.ui_components.text_fields.input

import android.content.Context
import android.view.Gravity
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
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
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput
import ge.space.ui.components.text_fields.input.utils.extension.doOnTextChanged
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData
import ge.space.ui.util.view_factory.extentions.getCurrencyViewData
import ge.space.ui.util.view_factory.extentions.getNumberEditTextViewData

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


                itemBinding.rgContent.setOnCheckedChangeListener { group, checkedId ->
                    itemBinding.simpleInput.contextView = when (checkedId) {
                        R.id.phoneMask -> SPViewData.SPMaskedEditTextData(
                            R.style.h700_bold_caps_text_field,
                            context.getString(R.string.phone_mask),
                            hint = R.string.enter_you_details_here
                        ).createView(context)
                        R.id.dateMask -> SPViewData.SPMaskedEditTextData(
                            R.style.h700_bold_caps_text_field,
                            context.getString(R.string.day_mask),
                            hint = R.string.enter_you_details_here
                        ).createView(context)
                        R.id.numberInput -> getNumberEditTextViewData(
                            context,
                            context.getString(R.string.enter_amount)
                        ).createView(context)
                        else -> SPViewData.SPEditTextData(
                            R.style.h700_bold_caps_text_field,
                            context.getString(R.string.enter_you_details_here)
                        ).createView(context)
                    } as EditText
                }

                itemBinding.rgLeading.setOnCheckedChangeListener { group, checkedId ->
                    itemBinding.simpleInput.leadingView = when (checkedId) {
                        R.id.primaryChip -> SPDefaultPrimaryChipData.getSmallChipData(
                            context, SPViewData.SPViewDataParams(
                                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
                            )
                        )
                            .createView(context)
                        R.id.phone -> SPViewData.SPTextData(
                            context.getString(R.string.phone_prefix),
                            textStyle = R.style.h600_bold_text_field_phone,
                            SPViewData.SPViewDataParams(
                                gravity = Gravity.CENTER_VERTICAL or Gravity.END,
                                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_4)/*,
                            paddingBottom = context.resources.getDimensionPixelSize(R.dimen.dimen_p_4),*/
                            )
                        ).createView(context)
                        R.id.image -> SPViewData.SPImageResourcesData(
                            R.drawable.ic_chat_message_24_regular,
                            SPViewData.SPViewDataParams(
                                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16),
                                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14)
                            )
                        ).createView(context)
                        else -> null
                    }
                }
                itemBinding.rgTrail.setOnCheckedChangeListener { group, checkedId ->
                    itemBinding.simpleInput.trailView = when (checkedId) {
                        R.id.trailPrimaryChip -> SPDefaultPrimaryChipData.getSmallChipData(
                            context, SPViewData.SPViewDataParams(
                                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
                                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
                            )
                        ).createView(context)
                        R.id.rbCurrency -> getCurrencyViewData(context, "$")
                            .createView(context)
                        R.id.removeIcon -> SPViewData.SPImageResourcesData(
                            R.drawable.ic_close_circle_24_filled,
                            SPViewData.SPViewDataParams(
                                paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
                                paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
                            )
                        )
                            .createView(context)
                        else -> null
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