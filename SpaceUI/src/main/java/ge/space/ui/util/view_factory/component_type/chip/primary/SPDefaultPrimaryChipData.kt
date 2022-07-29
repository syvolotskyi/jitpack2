package ge.space.ui.util.view_factory.component_type.chip.primary

import android.content.Context
import ge.space.spaceui.R
import ge.space.ui.util.view_factory.SPViewData

class SPDefaultPrimaryChipData {
    companion object {
        fun getSmallChipData(
            context: Context,
            params: SPViewData.SPViewDataParams? = null,
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                R.style.SPBankCardView_ChipPrimary_Small,
                params
            )
        }

        fun getEmptyChipData(
            context: Context
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                R.style.SPBankCardView_ChipPrimary_Medium
            )
        }

        fun getBigChipData(
            context: Context
        ): SPViewData.SPrimaryChipData {
            return SPViewData.SPrimaryChipData(
                R.style.SPBankCardView_ChipPrimary
            )
        }
    }
}