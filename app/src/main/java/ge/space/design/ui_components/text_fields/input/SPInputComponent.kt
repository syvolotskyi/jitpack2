package ge.space.design.ui_components.text_fields.input

import android.content.Context
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.spacedesignsystem.R
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

            val context = layoutBinding.simpleInput.context

            with(layoutBinding.simpleInput) {
                setupInputTextWithDone(this, environment.context)
                doOnTextChanged { text, _, _, _ ->
                    isDistractive = false
                    if (text.toString() == TEXT_WATCHER_CHECK_TEXT) {
                        showToast(context, text.toString())
                    }
                }
            }

            layoutBinding.cbMandatory.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.simpleInput.inputMandatory = isChecked
            }

            layoutBinding.cbDisable.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.simpleInput.isEnabled = !isChecked
            }

            layoutBinding.cbEnableDescription.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.simpleInput.descriptionText = if (isChecked) {
                    layoutBinding.simpleInput.resources.getString(R.string.description)
                } else {
                    EMPTY_TEXT
                }
            }
            layoutBinding.cbDistractive.setOnCheckedChangeListener { _, isChecked ->
                layoutBinding.simpleInput.isDistractive = isChecked
            }


            layoutBinding.typeOfInput.setOnCheckedChangeListener { _, checkedId ->
                when (checkedId) {
                    R.id.primarySimple ->
                        with(layoutBinding.simpleInput) {
                            setupStartViewByType(SPStartViewType.SPNoneViewType)
                            setupContentInputViewByType(
                                SPTextInputViewType.SPEditTextViewType()
                            )
                            setupEndViewByType(SPEndViewType.SPNoneViewType)
                        }

                    R.id.primarySimpleHint ->
                        with(layoutBinding.simpleInput) {
                            setupStartViewByType(SPStartViewType.SPNoneViewType)
                            setupContentInputViewByType(
                                SPTextInputViewType.SPEditTextViewType(
                                    hint = context.getString(
                                        R.string.enter_you_details_here
                                    )
                                )
                            )
                            setupEndViewByType(SPEndViewType.SPNoneViewType)
                        }


                    R.id.primaryRemovableSimple -> {
                        with(layoutBinding.simpleInput) {
                            setupStartViewByType(SPStartViewType.SPNoneViewType)
                            setupContentInputViewByType(
                                SPTextInputViewType.SPEditTextViewType(
                                    hint = context.getString(
                                        R.string.enter_you_details_here
                                    )
                                ),
                            )
                            setupEndViewByType(SPEndViewType.SPRemovableViewType)
                        }
                    }

                    R.id.primaryIconSimple -> {
                        with(layoutBinding.simpleInput) {
                            setupStartViewByType(SPStartViewType.SPImageViewType())
                            setupContentInputViewByType(
                                SPTextInputViewType.SPEditTextViewType(
                                    hint = context.getString(
                                        R.string.enter_you_details_here
                                    )
                                ),
                            )
                            setupEndViewByType(SPEndViewType.SPRemovableViewType)
                        }
                    }

                    else -> layoutBinding.simpleInput.setupContentInputViewByType(
                        SPTextInputViewType.SPEditTextViewType(hint = context.getString(R.string.enter_you_details_here))
                    )
                }
            }

            layoutBinding.textInput.doOnTextChanged { text, _, _, _ ->
                with(layoutBinding.simpleInput) {
                    labelText = text.toString()
                    descriptionText = text.toString()
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