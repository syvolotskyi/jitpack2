package ge.space.ui.components.banners.base

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.viewbinding.ViewBinding
import ge.space.ui.base.SPBaseView.Companion.EMPTY_TEXT

abstract class SPBannerBaseView<VB : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    protected val binding: VB

    private val _binding by lazy {
        getViewBinding()
    }

    var bannerTitle: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerTitle(value)
        }

    var bannerSubtitle: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerSubTitle(value)
        }

    var bannerDescription: String = EMPTY_TEXT
        set(value) {
            field = value

            updateBannerDescription(value)
        }

    var titleVisibility: Boolean = true
        set(value){
            field = value

            updateTitleVisibility(value)
        }

    var subTitleVisibility: Boolean = true
        set(value){
            field = value

            updateSubTitleVisibility(value)
        }

    var descriptionVisibility: Boolean = true
        set(value){
            field = value

            updateDescriptionVisibility(value)
        }


    init {
        binding = _binding
    }

    protected abstract fun getViewBinding(): VB

    protected abstract fun updateBannerTitle(text: String)

    protected abstract fun updateBannerSubTitle(text: String)

    protected abstract fun updateBannerDescription(text: String)

    protected abstract fun updateTitleVisibility(value: Boolean)

    protected abstract fun updateSubTitleVisibility(value: Boolean)

    protected abstract fun updateDescriptionVisibility(value: Boolean)

    protected abstract fun setBannerStyle(@StyleRes defStyleRes: Int)


}