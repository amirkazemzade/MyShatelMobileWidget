package me.amirkazemzade.myshatelmobilewidget.ui.login

import android.graphics.BitmapFactory
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlin.io.encoding.Base64

fun String.toImageBitmap(): ImageBitmap {
    val decodedBase64 = Base64.decode(this)
    val decodedImage = BitmapFactory.decodeByteArray(decodedBase64, 0, decodedBase64.size)
    return decodedImage.asImageBitmap()
}