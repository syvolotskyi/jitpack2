package ge.space.design.main

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.view.View
import androidx.fragment.app.Fragment


enum class ComponentLauncher(private val target: Class<*>) {

    ActivityLauncher(Activity::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
        ) {
            val intent = Intent(environment.context, componentClass)
            display.show(intent)
        }
    },

    FragmentLauncher(Fragment::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
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
                "Failed to instantiate fragment: ${componentClass.name}"
            }
            display.show(fr)
        }
    },

    DialogLauncher(Dialog::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
        ) {
            val dialog = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environment.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? Dialog
            checkNotNull(dialog) {
                "Failed to instantiate dialog: ${componentClass.name}"
            }
            display.show(dialog)
        }
    },

    ViewLauncher(View::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
        ) {
            val view = try {
                componentClass.getConstructor(Context::class.java)
                    .newInstance(environment.context)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? View
            checkNotNull(view) {
                "Failed to instantiate view: ${componentClass.name}"
            }
            display.show(view)
        }
    },

    ComponentFactoryLauncher(ComponentFactory::class.java) {
        override fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
        ) {
            val factory = try {
                componentClass.getConstructor().newInstance()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            } as? ComponentFactory
            checkNotNull(factory) {
                "Failed to instantiate component factory: ${componentClass.name}"
            }
            when (val component = factory.create(environment)) {
                is Intent -> display.show(component)
                is Fragment -> display.show(component)
                is Dialog -> display.show(component)
                is View -> display.show(component)
                is LaunchAction -> display.show(component)
                else -> showcaseError("Unknown component type")
            }
        }
    };

    protected open fun canLaunch(componentClass: Class<*>): Boolean {
        return target.isAssignableFrom(componentClass)
    }

    protected abstract fun launch(
        componentClass: Class<*>,
        display: ShowCaseDisplay,
        environment: ShowCaseEnvironment
    )

    companion object {

        private val LAUNCHERS = values()

        @Throws(Exception::class)
        fun launch(
            componentClass: Class<*>,
            display: ShowCaseDisplay,
            environment: ShowCaseEnvironment
        ) {
            LAUNCHERS.find { it.canLaunch(componentClass) }
                ?.launch(componentClass, display, environment)
                ?: showcaseError("Unknown component type")
        }
    }
}