package ge.space.ui.components.tab_switcher

import android.content.Context
import android.content.res.TypedArray
import android.transition.TransitionManager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import ge.space.ui.util.extension.onClick
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.constraintlayout.widget.ConstraintSet.PARENT_ID
import androidx.core.content.withStyledAttributes
import androidx.core.view.size
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpSegmentControlLayoutBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.base.SPViewStyling
import ge.space.ui.util.extension.getColorFromAttribute
import ge.space.ui.util.extension.handleAttributeAction

class SPSegmentControl @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
    @StyleRes defStyleRes: Int = R.style.SPSegmentControl
) : SPBaseView(context, attrs, defStyleAttr,defStyleRes), SPViewStyling {

    private val binding by lazy {
        SpSegmentControlLayoutBinding.inflate(LayoutInflater.from(context), this)
    }
    private var list = arrayListOf<SPSegmentNode>()

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.SPSegmentControl,
            defStyleAttr,
            defStyleRes
        ) {
            withStyledAttributes()
        }
    }

    override fun setViewStyle(@StyleRes newStyle: Int) {
        context.withStyledAttributes(newStyle, R.styleable.SPSegmentControl) {
            withStyledAttributes()
        }
    }

    fun addTab(title: String) {
        val lastItem = if (size > 0) list[size - 1] else null
        val view = TextView(context).apply {
            id = View.generateViewId()
            text = title
        }

        binding.parent.addView(view)

        ConstraintSet().apply {
            clone(binding.parent);
            connect(view.id, ConstraintSet.START, lastItem?.data?.id ?: PARENT_ID, if (lastItem == null) ConstraintSet.START else ConstraintSet.END, 0)
            connect(R.id.imageView, ConstraintSet.TOP, PARENT_ID, ConstraintSet.TOP, 0)
            applyTo(binding.parent)
        }
        list.add(SPSegmentNode(view))
    }

    private fun TypedArray.withStyledAttributes() {

 /*       getResourceId(
            R.styleable.SPSegmentControl_activeTextAppearance,
            SPBaseView.DEFAULT_OBTAIN_VAL
        )
            .handleAttributeAction(SPBaseView.DEFAULT_OBTAIN_VAL) {
                binding.selectedItem.textAppearance = it
            }*/

        binding.chip1.onClick{
            ConstraintSet().apply {
                clone(binding.parent);
                binding.selectedItem.text = "tab1"
                connect(binding.selectedItem.id, ConstraintSet.START, binding.chip1.id ,  ConstraintSet.START, 0)
                connect(binding.selectedItem.id, ConstraintSet.END, binding.chip1.id ,  ConstraintSet.END, 0)
                connect(binding.selectedItem.id, ConstraintSet.BOTTOM, PARENT_ID ,  ConstraintSet.BOTTOM, 0)


                TransitionManager.beginDelayedTransition(binding.parent)
                applyTo(binding.parent)
            }
        }
        binding.chip2.onClick{
            ConstraintSet().apply {
                clone(binding.parent);
                binding.selectedItem.text = "tab2"
                connect(binding.selectedItem.id, ConstraintSet.START, binding.chip2.id ,  ConstraintSet.START, 0)
                connect(binding.selectedItem.id, ConstraintSet.END, binding.chip2.id ,  ConstraintSet.END, 0)
                connect(binding.selectedItem.id, ConstraintSet.BOTTOM, PARENT_ID,  ConstraintSet.BOTTOM, 0)

                TransitionManager.beginDelayedTransition(binding.parent)
                applyTo(binding.parent)
            }
        }
        binding.chip3.onClick{
            ConstraintSet().apply {
                clone(binding.parent)
                binding.selectedItem.text = "tab3"
                connect(binding.selectedItem.id, ConstraintSet.START, binding.chip3.id ,  ConstraintSet.START, 0)
                connect(binding.selectedItem.id, ConstraintSet.END, binding.chip3.id ,  ConstraintSet.END, 0)
                connect(binding.selectedItem.id, ConstraintSet.BOTTOM, PARENT_ID ,  ConstraintSet.BOTTOM, 0)

                TransitionManager.beginDelayedTransition(binding.parent)
                applyTo(binding.parent)
            }
        }
    }
}