package ge.space.ui.base

/**
 * Interface to add possibility set distractive to View.
 *
 *  For example, we have two buttons - "Accept" and "Decline",
 *  and in our case "decline" buttons is with distractive = true attribute
 */
interface SPDistractiveMode {
    var isDistractive: Boolean
    fun handleDistractiveState()
}