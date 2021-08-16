package ge.space.design.main.util

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.annotation.StyleRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.fragment.app.FragmentActivity

class SPShowCaseEnvironment(
    val context: Context
) {
    fun requireActivity(): Activity {
        return (context as? Activity) ?: showcaseError(FAILED_REQUIRE_ACTIVITY)
    }

    fun requireFragmentActivity(): FragmentActivity {
        return (context as? FragmentActivity) ?: showcaseError(FAILED_REQUIRE_ACTIVITY)
    }

    fun requireAppCompatActivity(): AppCompatActivity {
        return (context as? AppCompatActivity) ?: showcaseError(FAILED_REQUIRE_APPCOMPAT_ACTIVITY)
    }

    fun requireLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    fun requireThemedLayoutInflater(@StyleRes styleResId: Int): LayoutInflater {
        return requireLayoutInflater().cloneInContext(ContextThemeWrapper(context, styleResId))
    }

    companion object {
        private const val FAILED_REQUIRE_ACTIVITY = "Failed require Activity"
        private const val FAILED_REQUIRE_APPCOMPAT_ACTIVITY = "Failed require AppCompatActivity"
    }
}

