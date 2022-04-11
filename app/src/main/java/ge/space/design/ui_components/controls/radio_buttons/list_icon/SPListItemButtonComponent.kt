package ge.space.design.ui_components.controls.radio_buttons.list_icon

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutListItemButtonsViewShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.controls.radio.list_item.extentions.setData

class SPListItemButtonComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.list_item_button

    override fun getDescriptionResId() = R.string.list_item_button_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutListItemButtonsViewShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )

            SPListItemStyles.georgian.apply {
                layoutBinding.radio1.setData(
                    environment.context.getString(titleId),
                    url
                )
            }

            SPListItemStyles.english.apply {
                layoutBinding.radio2.setData(
                    environment.context.getString(titleId),
                    url
                )
            }

            SPListItemStyles.ukraine.apply {
                layoutBinding.radio3.setData(
                    environment.context.getString(titleId),
                    url
                )
            }

            layoutBinding.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                when(checkedId){
                    layoutBinding.radio1.id -> {
                        Toast.makeText(environment.context, "Pressed 1", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.radio2.id -> {
                        Toast.makeText(environment.context, "Pressed 2", Toast.LENGTH_SHORT).show()
                    }
                    layoutBinding.radio3.id -> {
                        Toast.makeText(environment.context, "Pressed 3", Toast.LENGTH_SHORT).show()
                    }
                }
            }


            return layoutBinding.root
        }
    }
}