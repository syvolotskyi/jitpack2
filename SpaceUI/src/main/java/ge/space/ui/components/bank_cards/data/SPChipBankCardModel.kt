package ge.space.ui.components.bank_cards.data

import ge.space.ui.components.bank_cards.chip.empty.SPEmptyChip
import ge.space.ui.components.support.SPPlaceholder

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
     * For small chips
     */
    Small,
}

/**
 * Info about [SPChipIcon] appearance
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
     * For big size 40x40dp
     */
    Big,

    /**
     * For x_medium size 32x32dp
     */
    XMedium,

    /**
     * For medium size 24x24dp
     */
    Medium,

    /**
     * For x_small size 20x20dp
     */
    XSmall,

    /**
     * For small size 16x16dp
     */
    Small
}