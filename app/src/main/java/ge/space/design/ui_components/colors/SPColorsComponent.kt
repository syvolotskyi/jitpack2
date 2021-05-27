package ge.space.design.ui_components.colors

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.spacedesignsystem.R
import com.example.spacedesignsystem.databinding.ItemColorBinding
import ge.space.design.main.ComponentFactory
import ge.space.design.main.ShowCaseComponent
import ge.space.design.main.ShowCaseEnvironment
import ge.space.design.main.SimpleListAdapter
import kotlin.math.roundToInt


class SPColorsComponent : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.component_colors

    override fun getDescriptionResId(): Int = R.string.component_colors_description

    override fun getComponentClass(): Class<*>? = Factory::class.java

    class Factory : ComponentFactory {
        override fun create(environment: ShowCaseEnvironment): Any {
            val recyclerView = RecyclerView(environment.context)
            recyclerView.layoutManager = LinearLayoutManager(environment.context)
            recyclerView.addItemDecoration(
                DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
            )

            val adapter = SimpleListAdapter<ItemColorBinding, ColorRes>(
                Colors.list
            ).setup {
                onCreate { parent ->
                    ItemColorBinding.inflate(
                        environment.requireLayoutInflater(),
                        parent,
                        false
                    )
                }
                onBind { binding, (name, resId), position ->
                    val colorInt = ContextCompat.getColor(environment.context, resId)
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