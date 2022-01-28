package ge.space.design

import com.example.spacedesignsystem.R
import ge.space.design.main.ShowCaseComponent
import ge.space.design.ui_components.bank_cards.SPBankCardComponent
import ge.space.design.ui_components.banners.SPBannerComponent
import ge.space.design.ui_components.buttons.SPButtonComponent
import ge.space.design.ui_components.colors.SPColorsComponent
import ge.space.design.ui_components.controls.SPControlsComponent
import ge.space.design.ui_components.dialogs.SPDialogComponent
import ge.space.design.ui_components.marks.SPMarksComponent
import ge.space.design.ui_components.status_messages.SPTextViewComponent
import ge.space.design.ui_components.tab_navigation.SPTabNavigationComponent
import ge.space.design.ui_components.text_fields.SPTextFieldsComponent

object DesignSystemComponents : ShowCaseComponent {

    override fun getNameResId(): Int = R.string.all_component

    override fun getDescriptionResId(): Int = R.string.all_component_description

    override fun getDescriptionFormatArgs(): Array<Any> = arrayOf(1)

    override fun getSubComponents(): List<ShowCaseComponent> {
        return listOf(
            new { SPColorsComponent() },
            new { SPButtonComponent() },
            new { SPDialogComponent() },
            new { SPTextViewComponent() },
            new { SPControlsComponent() },
            new { SPTextFieldsComponent() },
            new { SPBannerComponent() },
            new { SPBankCardComponent() },
            new { SPMarksComponent() },
            new { SPTabNavigationComponent() }
        )
    }
}

/**
 * If you want component with "new" badge use [new].
 */
inline fun <reified T : ShowCaseComponent> new(block: () -> T): T =
    block().also { NEW_COMPONENTS.add(T::class.java) }

val NEW_COMPONENTS = mutableSetOf<Class<out ShowCaseComponent>>()