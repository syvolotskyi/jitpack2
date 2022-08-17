package ge.space.design.ui_components.buttons.pill

import android.content.Context
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutButtonPillShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.controls.radio.pill.SPPillItem
import io.mockk.InternalPlatformDsl.toStr

class SPPillItemComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.drawer_pill

    override fun getDescriptionResId() = R.string.drawer_pill_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            SpLayoutButtonPillShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {

                radioGroup.setOnCheckedChangeListener { _, id ->
                    when (id) {
                        toggleSwitch1.id -> {
                            Toast.makeText(environment.context, "Press 1", Toast.LENGTH_SHORT)
                                .show()
                        }
                        toggleSwitch2.id -> {
                            Toast.makeText(environment.context, "Press 2", Toast.LENGTH_SHORT)
                                .show()
                        }
                        toggleSwitch3.id -> {
                            Toast.makeText(environment.context, "Press 3", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
                }

                createRadioButtonsProgrammatically(environment.context, this)

                return root
            }
        }

        private fun createRadioButtonsProgrammatically(
            context: Context,
            binding: SpLayoutButtonPillShowcaseBinding
        ) {
            SPPillButtonStyles.list.onEach { buttonSample ->
                val resId = buttonSample.resId
                val itemBinding = SPPillItem(context, defStyleRes = resId).apply {
                    id = View.generateViewId()
                    title = buttonSample.title
                }

                binding.radioGroup2.addView(itemBinding)
            }

            binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
                Toast.makeText(context, checkedId.toStr(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}