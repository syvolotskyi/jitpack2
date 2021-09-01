package ge.space.ui.util.view_factory.component_type.chip.empty

import android.content.Context
import ge.space.spaceui.R
import ge.space.ui.components.bank_cards.data.SPEmptyChipStyle
import ge.space.ui.util.view_factory.SPViewData

class SPDefaultEmptyChipData {
    companion object {
        fun getSmallEmptyChipData(
            context: Context,
            chipStyle: SPEmptyChipStyle = SPEmptyChipStyle.White
        ): SPViewData.SPEmptyChipData {
            return SPViewData.SPEmptyChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_small),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width_small),
                chipStyle,
                R.style.SPBankCardView_ChipEmpty_Small_White
            )
        }

        fun getMediumEmptyChipData(
            context: Context,
            chipStyle: SPEmptyChipStyle = SPEmptyChipStyle.White
        ): SPViewData.SPEmptyChipData {
            return SPViewData.SPEmptyChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height_medium),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width_medium),
                chipStyle,
                R.style.SPBankCardView_ChipEmpty_Medium_White
            )
        }

        fun getBigEmptyChipData(
            context: Context,
            chipStyle: SPEmptyChipStyle = SPEmptyChipStyle.White
        ): SPViewData.SPEmptyChipData {
            return SPViewData.SPEmptyChipData(
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_height),
                context.resources.getDimensionPixelSize(R.dimen.sp_bank_chip_width),
                chipStyle,
                R.style.SPBankCardView_ChipEmpty_White
            )
        }
    }
}