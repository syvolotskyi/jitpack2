package ge.space.ui.components.bank_cards.data

import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.components.bank_cards.card_list.SPDefaultChipItem
import ge.space.ui.util.support.SPPlaceholder

/**
 * Changes [SPEmptyChip] appearance
 */
enum class SPEmptyChipStyle {

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
 * Info about a chip size
 */
enum class SPChipSize {

    /**
     * For big chips
     */
    Big,

    /**
     * For medium chips
     */
    Medium,
    /**
     * For small chips for dropdowns
     */
    Small,
}

/**
 * Info about [SPChipIconStyle] appearance
 */
enum class SPChipIconStyle {

    /**
     * For accent chips color
     */
    Accent,

    /**
     * For dark chips colors
     */
    Dark,
}

/**
 * Info about [SPPlaceholder] size
 */
enum class SPPlaceholderSize {

    /**
     * For big size 32x32dp
     */
    Big,


    /**
     * For medium size 20x20dp
     */
    Medium,

    /**
     * For small size 16x16dp
     */
    Small
}

/**
 * Info about [SPDefaultChipItem] chip
 */
sealed class SPDefaultChipData {

    /**
     * Sets a physical chip card
     */
    object SPPhysicalChip : SPDefaultChipData()

    /**
     * Sets a digital chip card with background
     */
    data class SPDigitalChip(
        val background: SPBankCardGradient
    ) : SPDefaultChipData()

    /**
     * Sets a chip icon with a plus image
     */
    object SPAddIconChip : SPDefaultChipData()
}