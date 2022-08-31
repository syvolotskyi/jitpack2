package ge.space.design.ui_components.tooltip

import android.content.Context
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.LinearLayout.*
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.SpLayoutTooltipShowcaseBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.util.SPShowCaseEnvironment
import ge.space.ui.components.tooltips.SPTooltipsView

class SPTooltipComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.tooltips

    override fun getDescriptionResId(): Int = R.string.tooltips_desc

    override fun getComponentClass(): Class<*> = SPFactory::class.java

    class SPFactory : SPComponentFactory {
        override fun create(environment: SPShowCaseEnvironment): Any {
            val layoutBinding = SpLayoutTooltipShowcaseBinding.inflate(
                environment.requireLayoutInflater()
            )
            SPTooltipsStyles.list.forEach { supportsLoading ->
                SPTooltipsView(environment.context).apply {
                    setViewStyle(supportsLoading.resId)
                    text = environment.context.getString(supportsLoading.title)
                    arrowDirection = supportsLoading.arrowDirection
                }.also { tooltipView ->
                    wrapWithBottomMargin(tooltipView, environment.context)
                    layoutBinding.smallTooltipsLayout.addView(tooltipView)
                }
            }

            return layoutBinding.root
        }

        private fun wrapWithBottomMargin(
            tooltipView: SPTooltipsView,
            context: Context
        ) {
            tooltipView.layoutParams = LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT
            ).also {
                it.setMargins(
                    0,
                    0,
                    0,
                    context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
                )
            }
        }
    }
}