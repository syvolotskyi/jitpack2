package ge.space.ui.components.bank_cards.data

import android.graphics.Color

/**
 * Applies a type of a gradient. It can be next states:
 *
 * <p>
 *     1. SPNoneGradient removes a gradient and applies one color
 *     2. SPRadial applies a radial gradient by colors
 *     3. SPLinear applies a linear gradient both colors and degrees of an angle
 * <p>
 */
sealed class SPBankCardGradient {

    /**
     * Applies homogeneous background applied by a color
     *
     * @property color [Int] instance which are related to the background. White is
     * a default color
     */
    data class SPNoneGradient(
        val color: Int = Color.WHITE
    ) : SPBankCardGradient()

    /**
     * Applies a background linear gradient by a list of colors with angle degrees.
     *
     * @property colors [ArrayList] instance of colors which are related to the gradient
     * @property degrees applies an angle
     */
    data class SPLinear(
        val colors: ArrayList<Int> = arrayListOf(Color.WHITE, Color.WHITE),
        val degrees: Float = 0f
    ) : SPBankCardGradient()

    /**
     * Applies a background radial gradient by a list of colors
     *
     * @property colors [ArrayList] instance of colors which are related to the gradient
     */
    data class SPRadial(
        val colors: ArrayList<Int> = arrayListOf(Color.WHITE, Color.WHITE),
    ) : SPBankCardGradient()
}

/**
 * Enum which applies a pay wave icon color
 */
enum class SPPayWaveType {

    /**
     * For dark background
     */
    Light,

    /**
     * For light background
     */
    Dark
}

/**
 * Applies a bank card status of availability
 */
enum class SPBankCardStatus {

    /**
     * No any popup
     */
    Available,

    /**
     * Popup with pending status
     */
    Pending,

    /**
     * Popup with blocked status
     */
    Blocked
}

/**
 * Applies an account number color which depends on a background
 */
enum class SPAccountNumberStyle {

    /**
     * For dark background
     */
    White,

    /**
     * For light background
     */
    Dark,
}

/**
 * Applies a type of bank card
 */
sealed class SPBankCardModel(open val name: String = "") {

    /**
     * Removes a title of a bank card type
     */
    data class SPDefault(
        override val name: String
    ) : SPBankCardModel()

    /**
     * Adds a static title of the physical type
     */
    data class SPPhysical(
        override val name: String
    ) : SPBankCardModel()

    /**
     * Adds a static title of the digital type with currency abbreviation
     */
    data class SPDigital(
        override val name: String,
        val currency : String
    ) : SPBankCardModel()
}