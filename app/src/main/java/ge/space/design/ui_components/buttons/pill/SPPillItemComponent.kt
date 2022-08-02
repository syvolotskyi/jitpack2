package ge.space.design.ui_components.buttons.pill

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpItemHorizontalBtnShowcaseBinding
import com.example.spacedesignsystem.databinding.SpLayoutButtonPillShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.design.ui_components.buttons.horizontal_button.SPHorizontalButtonStyles
import ge.space.ui.components.controls.radio.pill.SPPillItem

class SPPillItemComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.drawer_pill

    override fun getDescriptionResId() = R.string.drawer_pill_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutButtonPillShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            layoutBinding.radioGroup.setOnCheckedChangeListener { _, id ->
                when (id) {
                    layoutBinding.toggleSwitch1.id -> {
                        Toast.makeText(environment.context, "Press 1", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.toggleSwitch2.id -> {
                        Toast.makeText(environment.context, "Press 2", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.toggleSwitch3.id -> {
                        Toast.makeText(environment.context, "Press 3", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            SPPillButtonStyles.list.onEach { buttonSample ->
                val resId = buttonSample.resId
                val itemBinding = SPPillItem(environment.context, defStyleRes = resId).apply {
                    title = buttonSample.title
                    id = View.generateViewId()
                }

                layoutBinding.radioGroup2.addView(itemBinding)

                layoutBinding.radioGroup2.setOnCheckedChangeListener { _, id ->
                    val view = layoutBinding.radioGroup2.findViewById<SPPillItem>(id)
                    Toast.makeText(environment.context, view.title, Toast.LENGTH_SHORT).show()
                }
            }
            return layoutBinding.root
        }
    }
}