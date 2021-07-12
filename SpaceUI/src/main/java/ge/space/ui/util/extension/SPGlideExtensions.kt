package ge.space.ui.util.extension

import android.content.Context
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

fun Context.loadImageUrl(url: String, imageView: ImageView) {
    Glide.with(this)
        .load(url)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(imageView)
}

fun Context.loadRoundImageUrl(
    url: String,
    imageView: ImageView,
    cornerRadius: Int
) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(cornerRadius))
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(imageView)
}

fun Context.loadRoundImageUrlWithPlaceholder(
    url: String,
    imageView: ImageView,
    @DrawableRes placeholder: Int,
    cornerRadius: Int
) {
    Glide.with(this)
        .load(url)
        .transform(CenterCrop(), RoundedCorners(cornerRadius))
        .placeholder(placeholder)
        .error(placeholder)
        .diskCacheStrategy(DiskCacheStrategy.DATA)
        .into(imageView)
}