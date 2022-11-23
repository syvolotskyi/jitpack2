package ge.space.ui.components.bottomsheet.navigation

/*
* [SPNavigator] is interface which help to apply SPCommand
*/
interface SPNavigator {
    /*
    * @param [SPCommand] is  navigation command describes screens transition.
    */
    fun applyCommand(command: SPCommand)
}