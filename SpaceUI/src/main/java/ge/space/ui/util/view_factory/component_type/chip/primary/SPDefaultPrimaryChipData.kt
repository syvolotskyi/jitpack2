package ge.space.ui.util.view_factory.component_type.chip.primary

import android.content.Context
import androidx.annotation.StyleRes
import ge.space.spaceui.R
import ge.space.ui.util.view_factory.SPViewData

class SPDefaultPrimaryChipData {
    companion object {
        fun getSmallChipData(
            context: Context,
            params: SPViewData.SPViewDataParams? = null,
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width_small),
                R.style.SPBankCardView_ChipPrimary_Small,
                params
            )
        }

        fun getEmptyChipData(
            context: Context
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_medium),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width_medium),
                R.style.SPBankCardView_ChipPrimary_Medium
            )
        }

        fun getBigChipData(
            context: Context
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width),
                R.style.SPBankCardView_ChipPrimary
            )
        }
    }
}