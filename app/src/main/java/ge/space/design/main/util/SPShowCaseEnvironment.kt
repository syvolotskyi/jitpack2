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
        return (context as? Activity) ?: showcaseError("Failed require Activity")
    }

    fun requireFragmentActivity(): FragmentActivity {
        return (context as? FragmentActivity) ?: showcaseError("Failed require Activity")
    }

    fun requireAppCompatActivity(): AppCompatActivity {
        return (context as? AppCompatActivity) ?: showcaseError("Failed require AppCompatActivity")
    }

    fun requireLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(context)
    }

    fun requireThemedLayoutInflater(@StyleRes styleResId: Int): LayoutInflater {
        return requireLayoutInflater().cloneInContext(ContextThemeWrapper(context, styleResId))
    }
}