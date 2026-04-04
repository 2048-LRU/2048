package dev.game2048.app.utils

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import androidx.core.app.ShareCompat
import androidx.core.content.FileProvider
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

fun shareGameScore(context: Context, bitmap: Bitmap, score: Int) {
    try {
        val cachePath = File(context.cacheDir, "images").apply { mkdirs() }
        val file = File(cachePath, "score_2048.png")

        FileOutputStream(file).use { stream ->
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        }

        val uri = FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)

        ShareCompat.IntentBuilder(context)
            .setType("image/png")
            .setStream(uri)
            .setText("I've obtained a score of %,d".format(score))
            .setChooserTitle("Share with...")
            .startChooser()
    } catch (e: IllegalArgumentException) {
        Log.d("ShareScore", "image file not found, $e")
    } catch (e: IOException) {
        Log.d("ShareScore", "image directory not found or not permitted, $e")
    }
}
