package ge.space.design.ui_components.dialog

import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpDialogShowCaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment


class SPDialogComponent : SPShowCaseComponent {
    override fun getNameResId(): Int =
        R.string.component_dialog

    override fun getDescriptionResId(): Int =
        R.string.component_dialog_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding = SpDialogShowCaseBinding.inflate(environment.requireLayoutInflater())

            return binding.root
        }

    }
}