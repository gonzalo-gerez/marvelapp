package gonzalo.dev.marvelapp.common.util

import android.content.Context
import android.view.View
import android.view.animation.AnimationUtils
import gonzalo.dev.marvelapp.R

class AnimationUtil {

    companion object {

        fun slideCenterToLeft(context: Context, view: View) {
            val animator =
                AnimationUtils.loadAnimation(context, R.anim.design_trans_slide_out_to_left)
            view.startAnimation(animator)
        }
    }
}