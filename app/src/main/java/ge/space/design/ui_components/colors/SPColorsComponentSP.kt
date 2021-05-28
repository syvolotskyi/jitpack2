package ge.space.design.ui_components.colors

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.ItemColorBinding
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.main.ui.SimpleListAdapter
import ge.space.design.main.util.SPShowCaseEnvironment
import kotlin.math.roundToInt


class SPColorsComponentSP : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_colors

    override fun getDescriptionResId(): Int = R.string.component_colors_description

    override fun getComponentClass(): Class<*>? = FactorySP::class.java

    class FactorySP : SPComponentFactory {
        override fun create(environmentSP: SPShowCaseEnvironment): Any {
            val recyclerView = RecyclerView(environmentSP.context)
            recyclerView.layoutManager = LinearLayoutManager(environmentSP.context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            )

            val adapter = SimpleListAdapter<ItemColorBinding, ColorRes>(
                Colors.list
            ).setup {
                onCreate { parent ->
                    ItemColorBinding.inflate(
                        environmentSP.requireLayoutInflater(),
                        parent,
                        false
                    )
                }
                onBind { binding, (name, resId), position ->
                    val colorInt = ContextCompat.getColor(environmentSP.context, resId)
                    binding.colorView.setBackgroundColor(colorInt)
                    binding.colorDetails.text = buildString {
                        appendln("${position + 1}. $name")
                        // hex value
                        append("argb hex: #${Integer.toHexString(colorInt)} ")
                        // alpha channel
                        appendln("${(Color.alpha(colorInt) / 255f * 100).roundToInt()}%")
                        // rgb components
                        append(
                            "rgb: ${
                            Color.red(colorInt)} ${
                            Color.green(colorInt)} ${
                            Color.blue(colorInt)}"
                        )
                    }
                }
            }
            recyclerView.adapter = adapter

            return recyclerView
        }
    }
}