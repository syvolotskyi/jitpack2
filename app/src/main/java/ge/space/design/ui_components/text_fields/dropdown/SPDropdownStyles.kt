package ge.space.design.ui_components.text_fields.dropdown

import android.content.Context
import com.example.spacedesignsystem.R
import ge.space.ui.components.text_fields.input.base.getSmallCardView
import ge.space.ui.components.text_fields.input.dropdown.data.SPDropdownItemModel
import ge.space.ui.util.view_factory.SPViewData

object SPTextFieldsDropdownItems {
    private const val MASTER_CARD =
        "https://images.ctfassets.net/hrltx12pl8hq/3hkYVkjAgg0vIPv52JXRxU/9418da2d548684a4eec491114762f3b6/Summer_jpg.jpg?fit=fill&w=480&h=270"

    fun getList(context: Context) = listOf(
        SPDropdownItemModel(
            1,
            "Image from resources",
            SPViewData.SPImageResourcesData(
                R.drawable.ic_small_empty_chip_dark,
                SPViewData.SPViewDataParams(
                    paddingStart = context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_14),
                    paddingEnd = context.resources.getDimensionPixelSize(ge.space.spaceui.R.dimen.dimen_p_16)
                )
            )

        ),
        SPDropdownItemModel(
            2,
            "Image from url",
            SPViewData.SPImageUrlData("https://w7.pngwing.com/pngs/911/456/png-transparent-computer-icons-business-others-blue-angle-leaf-thumbnail.png")
        ),
        SPDropdownItemModel(
            3,
            "Primary chip",
            getSmallCardView(context)
        ),
    )
}
