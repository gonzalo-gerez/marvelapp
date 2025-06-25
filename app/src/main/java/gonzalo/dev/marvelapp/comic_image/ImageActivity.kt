package gonzalo.dev.marvelapp.comic_image

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import gonzalo.dev.marvelapp.common.mvvm.BaseActivity
import gonzalo.dev.marvelapp.common.mvvm.BaseViewModel
import gonzalo.dev.marvelapp.common.util.FrescoUtils
import gonzalo.dev.marvelapp.databinding.ActivityImageBinding

class ImageActivity : BaseActivity<BaseViewModel>() {
    companion object {
        const val EXTRA = "ia_extra"
    }

    private val binding by lazy { ActivityImageBinding.inflate(layoutInflater) }
    private var imageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.hasExtra(EXTRA)) {
            imageUrl = intent.getStringExtra(EXTRA)
            FrescoUtils.setImage(binding.fullImage, imageUrl)
        }
    }

    override fun createViewModelFactory(): BaseViewModel {
        val vm: BaseViewModel by viewModels()
        return vm
    }

    override fun getRootView(): View {
        return binding.root
    }
}