package ge.space.extensions

import android.os.Handler
import java.util.concurrent.TimeUnit

/**
 * Extension method to run block of code after specific Delay.
 */
fun runDelayed(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, action: () -> Unit) {
    Handler().postDelayed(action, timeUnit.toMillis(delay))
}

fun runDelayedWithTag(delay: Long, timeUnit: TimeUnit = TimeUnit.MILLISECONDS, tag: String? = null, action: (String?) -> Unit) {
    Handler().postDelayed({
        action.invoke(tag!!)
    }, timeUnit.toMillis(delay))
}