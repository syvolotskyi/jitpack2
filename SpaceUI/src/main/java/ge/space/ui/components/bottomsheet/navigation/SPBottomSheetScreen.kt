package ge.space.ui.components.bottomsheet.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory


fun interface Creator<A, R> {
    fun create(argument: A): R
}

interface SPBottomSheetScreen {
    val screenKey: String get() = this::class.java.name
    fun createFragment(factory: FragmentFactory): Fragment

    companion object {
        operator fun invoke(
            key: String? = null,
            fragmentCreator: Creator<FragmentFactory, Fragment>
        ) = object : SPBottomSheetScreen {
            override val screenKey = key ?: fragmentCreator::class.java.name
            override fun createFragment(factory: FragmentFactory) = fragmentCreator.create(factory)
        }
    }
}