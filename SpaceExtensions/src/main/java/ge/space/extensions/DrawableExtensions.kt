package ge.space.extensions

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.graphics.drawable.Drawable
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.IllegalArgumentException
import java.nio.ByteBuffer

fun Drawable.setBounds(left: Float, top: Float, right: Float, bottom: Float) {
    setBounds(
        left.toInt(),
        top.toInt(),
        right.toInt(),
        bottom.toInt()
    )
}

fun Bitmap.convertBitmapToByteArray(maxSizeKb: Int = Integer.MAX_VALUE): ByteArray {
    var quality = 100
    val scaledCardBytes = ByteArrayOutputStream()
    var bytes: ByteArray? = null

    fun compress(quality: Int) {
        this.compress(Bitmap.CompressFormat.JPEG, quality, scaledCardBytes)
        bytes = scaledCardBytes.toByteArray()
    }

    compress(quality)

    while (bytes!!.size / 1024 > maxSizeKb) {
        if (quality <= 3)
            throw IllegalArgumentException("Cannot decrease size to $maxSizeKb")

        quality = quality / 4 * 3
        scaledCardBytes.reset()
        compress(quality)
    }

    return bytes!!
}

fun Bitmap.resize(w: Number, h: Number): Bitmap {
    val width = width
    val height = height
    val scaleWidth = w.toFloat() / width
    val scaleHeight = h.toFloat() / height
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleHeight)
    if (width > 0 && height > 0) {
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}

fun Bitmap.resize(w: Number): Bitmap {
    val width = width
    val height = height
    val scaleWidth = w.toFloat() / width
    val matrix = Matrix()
    matrix.postScale(scaleWidth, scaleWidth)
    if (width > 0 && height > 0) {
        return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true)
    }
    return this
}

fun Bitmap.resizeMaxDimension(dimen: Float): Bitmap {
    if (dimen >= width && dimen >= height)
        return this

    val width = width.toFloat()
    val height = height.toFloat()

    val scaleRatio = if (width > height) dimen / width else dimen / height

    val matrix = Matrix().apply { postScale(scaleRatio, scaleRatio) }

    return Bitmap.createBitmap(this, 0, 0, width.toInt(), height.toInt(), matrix, true)
}

fun Bitmap.toByteArray(): ByteArray {
    val size = rowBytes * height
    val byteBuffer: ByteBuffer = ByteBuffer.allocate(size)
    copyPixelsToBuffer(byteBuffer)
    return byteBuffer.array()
}

fun replaceResizedJpgFile(filePath: String, maxImageSize: Int): Boolean {

    val bitmap = BitmapFactory.decodeFile(filePath)

    if (bitmap.height < maxImageSize && bitmap.width < maxImageSize) return true
    val ratio = Math.min(
        maxImageSize.toFloat() / bitmap.width,
        maxImageSize.toFloat() / bitmap.height
    )
    val width = Math.round(ratio * bitmap.width)
    val height = Math.round(ratio * bitmap.height)

    val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, false)

    val file = File(filePath)
    if (file.exists())
        file.delete()
    try {
        val out = FileOutputStream(file)
        scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out)
        out.flush()
        out.close()
    } catch (e: Exception) {
        return false
    }
    return true
}