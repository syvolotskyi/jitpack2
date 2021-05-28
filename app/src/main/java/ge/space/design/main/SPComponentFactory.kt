package ge.space.design.main

interface SPComponentFactory {
    /**
     * Returns a component object.
     *
     * The following types are supported:
     * Intent,
     * Fragment (DialogFragment),
     * Dialog,
     * View,
     * LaunchAction
     */
    fun create(environmentSP: SPShowCaseEnvironment): Any
}