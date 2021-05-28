package ge.space.design.main

import ge.space.design.main.util.SPShowCaseEnvironment

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