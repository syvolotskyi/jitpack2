package ge.space.design.ui_components.empty_state

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutEmptyStateShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment

class SPEmptyStateComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.empty_state

    override fun getDescriptionResId(): Int = R.string.empty_state_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val binding =
                SpLayoutEmptyStateShowcaseBinding.inflate(environment.requireLayoutInflater())
            binding.emptyStateWithButtonView.setOnButtonClickListener {
                Toast.makeText(environment.context, "Clicked", Toast.LENGTH_SHORT).show()
            }
            return binding.root
        }
    }
}