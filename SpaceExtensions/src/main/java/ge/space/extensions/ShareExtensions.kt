package ge.space.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager

@Throws(PackageManager.NameNotFoundException::class)
fun Context.shareFacebookMessenger(message: String){
    val share = Intent(Intent.ACTION_SEND)
    share.type = "text/plain"
    share.putExtra(Intent.EXTRA_TEXT, message)
    share.setPackage("com.facebook.orca")
    startActivity(Intent.createChooser(share, message))
}

@Throws(PackageManager.NameNotFoundException::class)
fun Context.shareTelegram(message: String) {
    val share = Intent(Intent.ACTION_SEND)
    share.type = "text/plain"
    share.putExtra(Intent.EXTRA_TEXT, message)
    share.setPackage("org.telegram.messenger")
    startActivity(Intent.createChooser(share, message))
}

@Throws(PackageManager.NameNotFoundException::class)
fun Context.shareWhatsUp(message: String){
    val share = Intent(Intent.ACTION_SEND)
    share.type = "text/plain"
    share.putExtra(Intent.EXTRA_TEXT, message)
    share.setPackage("com.whatsapp")
    startActivity(Intent.createChooser(share, message))
}

@Throws(PackageManager.NameNotFoundException::class)
fun Context.shareViber(message: String){
    val share = Intent(Intent.ACTION_SEND)
    share.type = "text/plain"
    share.putExtra(Intent.EXTRA_TEXT, message)
    share.setPackage("com.viber.voip")
    startActivity(Intent.createChooser(share, message))
}

@Throws(PackageManager.NameNotFoundException::class)
fun Context.shareEmail(message: String){
    val share = Intent(Intent.ACTION_SEND)
    share.type = "text/plain"
    share.putExtra(Intent.EXTRA_TEXT, message)
    share.setPackage("com.google.android.gm")
    startActivity(Intent.createChooser(share, message))
}