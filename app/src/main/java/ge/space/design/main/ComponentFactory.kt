package ge.space.design.main

interface ComponentFactory {
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
    fun create(environment: ShowCaseEnvironment): Any
}