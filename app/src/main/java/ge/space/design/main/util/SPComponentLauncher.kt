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

    ActivityLauncherSP(Activity::class.java) {
        override fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            val intent = Intent(environmentSP.context, componentClass)
            displaySP.show(intent)
        }
    },

    FragmentLauncherSP(Fragment::class.java) {
        override fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            val fr = try {
                val fragmentFactory =
                    environmentSP.requireAppCompatActivity().supportFragmentManager.fragmentFactory
                fragmentFactory.instantiate(
                    environmentSP.context.classLoader,
                    componentClass.name
                )
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            checkNotNull(fr) {
                "Failed to instantiate fragment: ${componentClass.name}"
            }
            displaySP.show(fr)
        }
    },

    DialogLauncherSP(Dialog::class.java) {
        override fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            val dialog = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environmentSP.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? Dialog
            checkNotNull(dialog) {
                "Failed to instantiate dialog: ${componentClass.name}"
            }
            displaySP.show(dialog)
        }
    },

    ViewLauncherSP(View::class.java) {
        override fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            val view = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environmentSP.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? View
            checkNotNull(view) {
                "Failed to instantiate view: ${componentClass.name}"
            }
            displaySP.show(view)
        }
    },

    SPComponentFactoryLauncher(SPComponentFactory::class.java) {
        override fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            val factory = try {
                componentClass.getConstructor().newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? SPComponentFactory
            checkNotNull(factory) {
                "Failed to instantiate component factory: ${componentClass.name}"
            }
            when (val component = factory.create(environmentSP)) {
                is Intent -> displaySP.show(component)
                is Fragment -> displaySP.show(component)
                is Dialog -> displaySP.show(component)
                is View -> displaySP.show(component)
                is SPLaunchAction -> displaySP.show(component)
                else -> showcaseError("Unknown component type")
            }
        }
    };

    protected open fun canLaunch(componentClass: Class<*>): Boolean {
        return target.isAssignableFrom(componentClass)
    }

    protected abstract fun launch(
            componentClass: Class<*>,
            displaySP: SPShowCaseDisplay,
            environmentSP: SPShowCaseEnvironment
    )

    companion object {

        private val LAUNCHERS = values()

        @Throws(Exception::class)
        fun launch(
                componentClass: Class<*>,
                displaySP: SPShowCaseDisplay,
                environmentSP: SPShowCaseEnvironment
        ) {
            LAUNCHERS.find { it.canLaunch(componentClass) }
                ?.launch(componentClass, displaySP, environmentSP)
                ?: showcaseError("Unknown component type")
        }
    }
}