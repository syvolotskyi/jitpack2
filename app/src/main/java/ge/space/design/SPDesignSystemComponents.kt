package ge.space.design

import com.example.spacedesignsystem.R
import ge.space.design.main.SPShowCaseComponent
import ge.space.design.ui_components.buttons.SPButtonComponent
import ge.space.design.ui_components.colors.SPColorsComponentSP
import ge.space.design.ui_components.text_fields.text_fields.SPTextFieldsComponent
import ge.space.design.ui_components.dialogs.SPDialogComponent

object DesignSystemComponents : SPShowCaseComponent {

    override fun getNameResId(): Int = R.string.all_component

    override fun getDescriptionResId(): Int = R.string.all_component_description

    override fun getDescriptionFormatArgs(): Array<Any> = arrayOf(1)

    override fun getSubComponents(): List<SPShowCaseComponent> {
        return listOf(
                new { SPColorsComponentSP() },
                new { SPButtonComponent() },
                new { SPDialogComponent() },
                new { SPTextFieldsComponent() }
        )
    }
}

/**
 * If you want component with "new" badge use [new].
 */
inline fun <reified T : SPShowCaseComponent> SPShowCaseComponent.new(block: () -> T): T =
        block().also { NEW_COMPONENTS.add(T::class.java) }

val NEW_COMPONENTS = mutableSetOf<Class<out SPShowCaseComponent>>()