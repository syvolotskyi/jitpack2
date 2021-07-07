package ge.space.extensions

import android.app.Activity
import android.content.*
import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.SystemClock
import android.provider.Settings
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.annotation.AttrRes
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import java.io.File

fun Context.createFileInInternalCache(path: String = System.currentTimeMillis().toString()): File? =
    Uri.parse(path)?.lastPathSegment?.let { filename ->
        File.createTempFile(filename, null, cacheDir)
    }

/**
 * Extension method to show play store
 */
fun Context.openAppInGooglePlay() {
    val appPackageName = packageName
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$appPackageName")
            )
        )
    } catch (e: ActivityNotFoundException) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")
            )
        )
    }
}

/**
 * Extension method to get a new Intent for an Activity class
 */
inline fun <reified T : Any> Context.intent() = Intent(this, T::class.java)

/**
 * Create an intent for [T] and apply a lambda on it
 */
inline fun <reified T : Any> Context.intent(body: Intent.() -> Unit): Intent {
    val intent = Intent(this, T::class.java)
    intent.body()
    return intent
}

/**
 * Extension method to browse for Context.
 */
fun Context.browse(url: String) {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.dial(tel: String?) = startActivity(Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel)))

/**
 * Extension method to send email for Context.
 */
fun Context.email(email: String) {
    val emailIntent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", email, null))
    this.startActivity(Intent.createChooser(emailIntent, "Send email..."))
}

fun Context.getColorRes(@ColorRes color: Int): Int {
    return ContextCompat.getColor(this, color)
}

fun Context.getDrawableRes(@DrawableRes drawableRes: Int) : Drawable? {
    return ContextCompat.getDrawable(this, drawableRes)
}

fun Context.sendSMS(number: String, body: String): Boolean {
    return try {
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("smsto:$number")
        intent.putExtra("sms_body", body)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }
}

fun Context.appInstalledOrNot(packageName: String): Boolean{
    val pm = packageManager
    return try {
        pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
        true
    } catch (e: PackageManager.NameNotFoundException) {
        false
    }
}

fun Context.paste(): String {
    val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val clip = clipboard.primaryClip ?: return ""
    val item = clip.getItemAt(0) ?: return ""
    return if (item.text.toString() != null) item.text.toString() else ""
}

fun Context.openAppSettings(){
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    val uri = Uri.fromParts("package", packageName, null)
    intent.data = uri
    startActivity(intent)
}

/**
 * Extension method to find a device width in pixels
 */
inline val Context.displayWidth: Int
    get() = resources.displayMetrics.widthPixels

/**
 * Extension method to find a device height in pixels
 */
inline val Context.displayHeight: Int
    get() = resources.displayMetrics.heightPixels

/**
 * Extension method to get displayMetrics in Context.displayMetricks
 */
inline val Context.displayMetricks: DisplayMetrics
    get() = resources.displayMetrics

fun Context.getSecureId() : String = Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID)

fun Context.dpToPx(dp: Float): Int =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.displayMetrics).toInt()

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Context.resolveColorByAttr(@AttrRes attr: Int) : Int {
    val typedValue = TypedValue()
    theme.resolveAttribute(
        attr, typedValue, true
    )

    return typedValue.data
}