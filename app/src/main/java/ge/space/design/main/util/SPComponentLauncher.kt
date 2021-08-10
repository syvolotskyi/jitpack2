package ge.space.design.main.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment
import ge.space.design.main.SPComponentFactory
import ge.space.design.main.SPLaunchAction
import ge.space.design.main.SPShowCaseDisplay

enum class SPComponentLauncher(private val target: Class<*>) {

    ActivityLauncher(Activity::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: SPShowCaseDisplay,
            environment: SPShowCaseEnvironment
        ) {
            val intent = Intent(environment.context, componentClass)
            display.show(intent)
        }
    },

    FragmentLauncher(Fragment::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: SPShowCaseDisplay,
            environment: SPShowCaseEnvironment
        ) {
            val fr = try {
                val fragmentFactory =
                    environment.requireAppCompatActivity().supportFragmentManager.fragmentFactory
                fragmentFactory.instantiate(
                    environment.context.classLoader,
                    componentClass.name
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            checkNotNull(fr) {
                "$INSTANTIATE_FRAGMENT_ERROR ${componentClass.name}"
            }
            display.show(fr)
        }
    },

    DialogLauncher(Dialog::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: SPShowCaseDisplay,
            environment: SPShowCaseEnvironment
        ) {
            val dialog = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environment.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? Dialog
            checkNotNull(dialog) {
                "$INSTANTIATE_DIALOG_ERROR ${componentClass.name}"
            }
            display.show(dialog)
        }
    },

    ViewLauncher(View::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: SPShowCaseDisplay,
            environment: SPShowCaseEnvironment
        ) {
            val view = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environment.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? View
            checkNotNull(view) {
                "$INSTANTIATE_VIEW_ERROR ${componentClass.name}"
            }
            display.show(view)
        }
    },

    SPComponentFactoryLauncher(SPComponentFactory::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: SPShowCaseDisplay,
            environment: SPShowCaseEnvironment
        ) {
            val factory = try {
                componentClass.getConstructor().newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? SPComponentFactory
            checkNotNull(factory) {
              "$SHOW_CASE_NULL_POINT_ERROR ${componentClass.name}"
            }
            when (val component = factory.create(environment)) {
                is Intent -> display.show(component)
                is Fragment -> display.show(component)
                is Dialog -> display.show(component)
                is View -> display.show(component)
                is SPLaunchAction -> display.show(component)
                else -> showcaseError(UNKNOWN_SHOW_CASE_ERROR)
            }
        }
    };

    protected open fun canLaunch(componentClass: Class<*>): Boolean {
        return target.isAssignableFrom(componentClass)
    }

    protected abstract fun launch(
        componentClass: Class<*>,
        display: SPShowCaseDisplay,
        environment: SPShowCaseEnvironment
    )

    companion object {

        private val LAUNCHERS = values()
        private const val UNKNOWN_SHOW_CASE_ERROR = "Unknown component type"
        private const val SHOW_CASE_NULL_POINT_ERROR = "Failed to instantiate component factory:"
        private const val INSTANTIATE_FRAGMENT_ERROR = "Failed to instantiate fragment:"
        private const val INSTANTIATE_DIALOG_ERROR   = "Failed to instantiate dialog:"
        private const val INSTANTIATE_VIEW_ERROR   = "Failed to instantiate view:"

        @Throws(Exception::class)
        fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            LAUNCHERS.find { it.canLaunch(componentClass) }
                ?.launch(componentClass, displaySP, environmentSP)
                ?: showcaseError(UNKNOWN_SHOW_CASE_ERROR)
        }
    }
}