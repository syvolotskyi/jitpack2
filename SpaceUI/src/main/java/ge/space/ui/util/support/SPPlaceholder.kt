package ge.space.ui.util.support

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.annotation.AttrRes
import androidx.annotation.StyleRes
import androidx.core.content.withStyledAttributes
import ge.space.extensions.setHeight
import ge.space.extensions.setWidth
import ge.space.spaceui.R
import ge.space.spaceui.databinding.SpPlaceholderBinding
import ge.space.ui.base.SPBaseView
import ge.space.ui.components.bank_cards.data.SPPlaceholderSize
import ge.space.ui.util.extension.loadImageUrl

/**
 * A placeholder with a logo which allows to change its size and load an image by its URL
 *
 * @property placeholderSize [SPPlaceholder] enum instance which allows to resize
 * @property logoUrl [String] instance which allows to load the image by it
 */
class SPPlaceholder @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0,
) : SPBaseView(context, attrs, defStyleAttr) {

    /**
     * Changes a size of the view
     */
    var placeholderSize: SPPlaceholderSize = SPPlaceholderSize.XSmall
        set(value) {
            field = value

            handleSize()
        }

    /**
     * Changes a logo URL which allows to load it
     */
    var logoUrl: String = EMPTY_TEXT
        set(value) {
            field = value

            loadLogo()
        }

    /**
     * Binds a view
     */
    private val binding =
        SpPlaceholderBinding.inflate(LayoutInflater.from(context), this)

    init {
        getContext().withStyledAttributes(
            attrs,
            R.styleable.ps_placeholder,
            defStyleAttr
        ) {
            placeholderSize =
                SPPlaceholderSize.values()[getInt(R.styleable.ps_placeholder_placeholder_size, DEFAULT_OBTAIN_VAL)]
        }
    }

    private fun handleSize() {
        reSizeRoot()
        reSizeLogo()
        setStyle(
            getStyleBySize()
        )
    }

    private fun reSizeRoot() {
        with(binding.vRoot) {
            setHeight(getRootDimenBySize())
            setWidth(getRootDimenBySize())
        }
    }

    private fun reSizeLogo() {
        with(binding.ivLogo) {
            setHeight(getRootDimenBySize())
            setWidth(getRootDimenBySize())
        }
    }

    private fun getRootDimenBySize() = resources.getDimension(
        when(placeholderSize) {
            SPPlaceholderSize.Big -> R.dimen.sp_bank_logo_placeholder_size_big
            SPPlaceholderSize.XMedium -> R.dimen.sp_bank_logo_placeholder_size_xmedium
            SPPlaceholderSize.Medium -> R.dimen.sp_bank_logo_placeholder_size_medium
            SPPlaceholderSize.XSmall -> R.dimen.sp_bank_logo_placeholder_size_xsmall
            SPPlaceholderSize.Small -> R.dimen.sp_bank_logo_placeholder_size_small
        }
    ).toInt()

    private fun getLogoDimenBySize() = resources.getDimension(
        when(placeholderSize) {
            SPPlaceholderSize.Big -> R.dimen.sp_placeholder_logo_size_big
            SPPlaceholderSize.XMedium -> R.dimen.sp_placeholder_logo_size_xmedium
            SPPlaceholderSize.Medium -> R.dimen.sp_placeholder_logo_size_medium
            SPPlaceholderSize.XSmall -> R.dimen.sp_placeholder_logo_size_xsmall
            SPPlaceholderSize.Small -> R.dimen.sp_placeholder_logo_size_small
        }
    ).toInt()

    private fun getStyleBySize() =
        when(placeholderSize) {
            SPPlaceholderSize.Big -> R.style.SPChip_PlaceHolder_Big
            SPPlaceholderSize.XMedium -> R.style.SPChip_PlaceHolder_XMedium
            SPPlaceholderSize.Medium -> R.style.SPChip_PlaceHolder_Medium
            SPPlaceholderSize.XSmall -> R.style.SPChip_PlaceHolder_XSmall
            SPPlaceholderSize.Small -> R.style.SPChip_PlaceHolder_Small
        }

    private fun loadLogo() {
        if (logoUrl.isNotEmpty()) {
            context.loadImageUrl(
                logoUrl,
                binding.ivLogo
            )
        }
    }
}