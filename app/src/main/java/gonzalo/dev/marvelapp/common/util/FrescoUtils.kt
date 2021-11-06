package gonzalo.dev.marvelapp.common.util

import android.util.Log
import android.webkit.URLUtil
import androidx.annotation.NonNull
import androidx.core.net.toUri
import com.facebook.drawee.backends.pipeline.Fresco
import com.facebook.drawee.view.SimpleDraweeView
import com.facebook.imagepipeline.request.ImageRequestBuilder
import javax.annotation.Nullable


object FrescoUtils {
    private const val TAG = "fataFrescoUtils"

    fun setImage(@NonNull view: SimpleDraweeView, @Nullable imageUri: String?) {
        if (imageUri.isNullOrEmpty() || !URLUtil.isValidUrl(imageUri)) {
            return
        }

        Log.i(TAG, "$imageUri")
        val request = ImageRequestBuilder.newBuilderWithSource(imageUri.toUri())
            .setProgressiveRenderingEnabled(true)
            .build()
        view.controller = Fresco.newDraweeControllerBuilder()
            .setTapToRetryEnabled(true)
            .setImageRequest(request)
            .setOldController(view.controller).build()
    }


}