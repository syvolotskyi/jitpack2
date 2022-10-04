package ge.space.design

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.amount.SPAmountComponent
import ge.space.design.ui_components.sandbox.SPSandboxComponent
import ge.space.design.ui_components.bank_cards.SPChipsComponent
import ge.space.design.ui_components.bank_cards.card.SPBankCardViewComponent
import ge.space.design.ui_components.banners.SPBannerComponent
import ge.space.design.ui_components.buttons.SPButtonComponent
import ge.space.design.ui_components.colors.SPColorsComponent
import ge.space.design.ui_components.controls.SPControlsComponent
import ge.space.design.ui_components.dialogs.SPDialogComponent
import ge.space.design.ui_components.empty_state.SPEmptyStateComponent
import ge.space.design.ui_components.marks.SPMarksComponent
import ge.space.design.ui_components.profile.SPProfileComponent
import ge.space.design.ui_components.tab_navigation.SPTabNavigationComponent
import ge.space.design.ui_components.text_fields.SPTextFieldsComponent
import ge.space.design.ui_components.text_view.SPTextViewComponent
import ge.space.design.ui_components.tooltip.SPTooltipComponent

object DesignSystemComponents : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.all_component

    override fun getDescriptionResId(): Int = R.string.all_component_description

    override fun getDescriptionFormatArgs(): Array<Any> = arrayOf(1)

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            new { SPSandboxComponent() },
            new { SPAmountComponent() },
            new { SPBankCardViewComponent() },
            new { SPBannerComponent() },
            new { SPButtonComponent() },
            new { SPChipsComponent() },
            new { SPColorsComponent() },
            new { SPControlsComponent() },
            new { SPDialogComponent() },
            new { SPEmptyStateComponent() },
            new { SPMarksComponent() },
            new { SPProfileComponent() },
            new { SPTabNavigationComponent() },
            new { SPTextFieldsComponent() },
            new { SPTextViewComponent() },
            new { SPTooltipComponent() },
        )
    }
}

/**
 * If you want component with "new" badge use [new].
 */
inline fun <reified T : ShowCaseComponent> new(block: () -> T): T =
    block().also { NEW_COMPONENTS.add(T::class.java) }

val NEW_COMPONENTS = mutableSetOf<Class<out ShowCaseComponent>>()