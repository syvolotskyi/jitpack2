package ge.space.ui.components.text_fields.input.base

import ge.space.spaceui.R
import ge.space.ui.util.view_factory.SPViewData
import ge.space.ui.util.view_factory.SPViewFactory.Companion.createView
import ge.space.ui.util.view_factory.component_type.chip.primary.SPDefaultPrimaryChipData

fun SPTextFieldInput.setupTextInputPrimary(isRemovable:Boolean) {
    if (isRemovable)
        trailView =SPViewData.SPImageResourcesData(
        R.drawable.ic_close_circle_24_filled,
        SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_14),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_16)
        )
    )
        .createView(context)
}

fun SPTextFieldInput.setupPhoneMaskInput() {

}

fun SPTextFieldInput.setupDateMaskInput() {

}

fun SPTextFieldInput.setupCardView() {
    leadingView = SPDefaultPrimaryChipData.getSmallChipData(
        context, SPViewData.SPViewDataParams(
            paddingStart = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12),
            paddingEnd = context.resources.getDimensionPixelSize(R.dimen.dimen_p_12)
        )
    )
        .createView(context)
}