package ge.space.design.ui_components.tab_navigation.tab_switcher

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTabSwitcherShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.controls.radio.pill.SPPillItem
import ge.space.ui.util.extension.getLayoutParamsFromStyle
import io.mockk.InternalPlatformDsl.toStr

class SPTabSwitcherComponent : ShowCaseComponent {

    override fun getNameResId() = R.string.tab_switcher

    override fun getDescriptionResId() = R.string.tab_switcher_description

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            SpLayoutTabSwitcherShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            ).apply {
                radioGroup.setOnCheckedChangeListener { _, id ->
                    when (id) {
                        toggleSwitch1.id ->
                            Toast.makeText(environment.context, "Press 1", Toast.LENGTH_SHORT)
                                .show()
                        toggleSwitch2.id ->
                            Toast.makeText(environment.context, "Press 2", Toast.LENGTH_SHORT)
                                .show()
                        toggleSwitch3.id ->
                            Toast.makeText(environment.context, "Press 3", Toast.LENGTH_SHORT)
                                .show()
                        toggleSwitch4.id ->
                            Toast.makeText(environment.context, "Press 4", Toast.LENGTH_SHORT)
                                .show()

                    }
                }
                createTabsButtonsProgrammatically(environment.context, this)

                return root
            }
        }

        private fun createTabsButtonsProgrammatically(
            context: Context,
            binding: SpLayoutTabSwitcherShowcaseBinding
        ) {
            SPTabSwitcherStyles.list.onEach { buttonSample ->
                val resId = buttonSample.resId
                val itemBinding = SPPillItem(context, null, defStyleRes = resId).apply {
                    id = View.generateViewId()
                    title = buttonSample.title
                    layoutParams = getLayoutParamsFromStyle(context, resId)
                    isChecked = buttonSample.isChecked
                }

                binding.radioGroup2.addView(itemBinding)
            }

            binding.radioGroup2.setOnCheckedChangeListener { group, checkedId ->
                Toast.makeText(context, checkedId.toStr(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}
