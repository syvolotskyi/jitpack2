package ge.space.design.ui_components.dialogs

import android.content.Context
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpDialogShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.dialogs.*
import ge.space.ui.components.dialogs.data.*
import ge.space.ui.components.dialogs.dialog_buttons.SPDialogBottomVerticalButton
import ge.space.ui.components.text_fields.input.base.SPTextFieldInput

class SPDialogComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_dialog

    override fun getDescriptionResId(): Int = R.string.component_dialog_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpDialogShowCaseBinding.inflate(environment.requireLayoutInflater())
            val activity = environment.requireFragmentActivity()
            val buttonConfigs = createButtonsConfigs(activity)
            val multipleButtonConfigs = createMultipleButtonsConfigs(activity)
            val editTextButtonConfigs = createEditTextTwiceButtonConfigs(activity)

            with(binding) {
                showButton.setOnClickListener {
                    handleDialogs(
                        activity,
                        radioGroup,
                        titleInput,
                        infoInput,
                        buttonConfigs,
                        multipleButtonConfigs,
                        editTextButtonConfigs
                    )
                }
            }

            return binding.root
        }

        private fun handleDialogs(
            fragmentActivity: FragmentActivity,
            radioGroup: RadioGroup,
            titleInput: SPTextFieldInput,
            infoInput: SPTextFieldInput,
            buttonConfigs: ArrayList<SPDialogInfoHolder>,
            multipleButtonConfigs: ArrayList<SPDialogInfoHolder>,
            editTextButtonConfigs: ArrayList<SPEditTextDialogInfoHolder>
        ) {
            when(radioGroup.checkedRadioButtonId) {
                R.id.title ->
                    showTitleDialog(fragmentActivity, titleInput)
                R.id.label ->
                    showLabelDialog(fragmentActivity, infoInput)
                R.id.title_and_label ->
                    showStandardInfoDialog(fragmentActivity, titleInput, infoInput)
                R.id.title_label_twice_buttons ->
                    showQuestionnaireDialog(fragmentActivity, titleInput, infoInput, buttonConfigs)
                R.id.title_label_multiple_buttons ->
                    showMultipleButtonDialog(
                        fragmentActivity,
                        titleInput,
                        infoInput,
                        multipleButtonConfigs
                    )
                R.id.edit_text_dialog ->
                    showEditTextDialog(
                        fragmentActivity,
                        titleInput,
                        editTextButtonConfigs
                    )
            }
        }

        private fun showEditTextDialog(
            fragmentActivity: FragmentActivity,
            titleInput: SPTextFieldInput,
            editTextButtonConfigs: ArrayList<SPEditTextDialogInfoHolder>
        ) {
            fragmentActivity.showEditTextDialog(
                SPEditTextDialogInfo(
                    titleInput.text,
                    editTextButtonConfigs
                )
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun showMultipleButtonDialog(
            fragmentActivity: FragmentActivity,
            titleInput: SPTextFieldInput,
            infoInput: SPTextFieldInput,
            multipleButtonConfigs: ArrayList<SPDialogInfoHolder>
        ) {
            fragmentActivity.showMultipleButtonDialog(
                SPDialogInfo(
                    titleInput.text,
                    infoInput.text,
                    multipleButtonConfigs
                ),
                SPDialogIcon.Alert(R.attr.accent_magenta_ui)
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun showQuestionnaireDialog(
            fragmentActivity: FragmentActivity,
            titleInput: SPTextFieldInput,
            infoInput: SPTextFieldInput,
            buttonConfigs: ArrayList<SPDialogInfoHolder>
        ) {
            fragmentActivity.showQuestionnaireDialog(
                SPDialogInfo(
                    titleInput.text,
                    infoInput.text,
                    buttonConfigs,
                ),
                SPDialogIcon.Info()
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun showStandardInfoDialog(
            fragmentActivity: FragmentActivity,
            titleInput: SPTextFieldInput,
            infoInput: SPTextFieldInput
        ) {
            fragmentActivity.showStandardInfoDialog(
                SPDialogInfo(
                    titleInput.text,
                    infoInput.text
                )
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun showLabelDialog(
            fragmentActivity: FragmentActivity,
            infoInput: SPTextFieldInput
        ) {
            fragmentActivity.showLabelDialog(
                infoInput.text
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun showTitleDialog(
            fragmentActivity: FragmentActivity,
            titleInput: SPTextFieldInput
        ) {
            fragmentActivity.showTitleDialog(
                titleInput.text
            ) {
                Toast.makeText(fragmentActivity, "dismissed", Toast.LENGTH_SHORT).show()
            }
        }

        private fun createButtonsConfigs(context: Context) =
            arrayListOf(
                SPDialogInfoHolder(
                    "Label 1",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    Toast.makeText(context, "hello from label 1", Toast.LENGTH_SHORT).show()
                },
                SPDialogInfoHolder(
                    "Label 2",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    Toast.makeText(context, "hello from label 2", Toast.LENGTH_SHORT).show()
                }
            )

        private fun createMultipleButtonsConfigs(context: Context) =
            arrayListOf(
                SPDialogInfoHolder(
                    "Label 1",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    Toast.makeText(context, "hello from label 1", Toast.LENGTH_SHORT).show()
                },
                SPDialogInfoHolder(
                    "Label 2",
                    SPDialogBottomVerticalButton.BottomButtonType.Remove
                ) {
                    Toast.makeText(context, "hello from label 2", Toast.LENGTH_SHORT).show()
                },
                SPDialogInfoHolder(
                    "Label 3",
                    SPDialogBottomVerticalButton.BottomButtonType.Cancel
                ) {
                    Toast.makeText(context, "hello from label 3", Toast.LENGTH_SHORT).show()
                }
            )

        private fun createEditTextTwiceButtonConfigs(context: Context) =
            arrayListOf(
                SPEditTextDialogInfoHolder(
                    "Label 1",
                    SPDialogBottomVerticalButton.BottomButtonType.Cancel
                ) {
                    Toast.makeText(context, "hello from label 1", Toast.LENGTH_SHORT).show()
                },
                SPEditTextDialogInfoHolder(
                    "Label 2",
                    SPDialogBottomVerticalButton.BottomButtonType.Default
                ) {
                    Toast.makeText(context, it.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            )
    }
}