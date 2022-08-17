package ge.space.design.ui_components.empty_state

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutEmptyStateShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.empty_state.SPEmptyStateView

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
            SPEmptyStateStyles.list.forEach { emptyStyle ->
                SPEmptyStateView(environment.context).apply {
                    setViewStyle(emptyStyle.resId)
                    titleText = environment.context.getString(emptyStyle.title)
                    descText = environment.context.getString(emptyStyle.description)
                    buttonText = environment.context.getString(emptyStyle.button)
                }.also {
                    binding.containerViews.addView(it)
                }
            }
            return binding.root
        }
    }
}