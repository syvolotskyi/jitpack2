package ge.space.design.ui_components.sandbox

import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutSandboxShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.tab_switcher.SPSegmentControl

class SPSandboxComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.sandbox_showcase

    override fun getDescriptionResId(): Int = R.string.sandbox_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutSandboxShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                segmentControl.apply {
                    setTabs(listOf("Tab 1","Tab 2","Tab 3"))
                    setSelectedTab(SPSegmentControl.FIRST_TAB)
                    setOnTabSelectedListener { title, key ->
                        Toast.makeText(
                            environment.requireActivity(),
                            "$title\nKey:$key",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

            return layoutBinding.root
        }
    }
}