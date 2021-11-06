package gonzalo.dev.marvelapp.home

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import gonzalo.dev.marvelapp.R
import gonzalo.dev.marvelapp.common.extensions.addFragment
import gonzalo.dev.marvelapp.common.extensions.replaceFragment
import gonzalo.dev.marvelapp.common.extensions.showFromStack
import gonzalo.dev.marvelapp.common.mvvm.BaseActivity
import gonzalo.dev.marvelapp.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity<HomeViewModel>() {

    private val binding: ActivityHomeBinding by lazy {
        ActivityHomeBinding.inflate(
            layoutInflater
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.title = getString(R.string.home_title)

        getViewModel().setViewStateAsLoading()

        getViewModel().isDualPane = binding.secondPane?.visibility == View.VISIBLE
        if (getViewModel().isDualPane) {
            showFromStack(CharacterDetailFragment::class.java.simpleName)
                ?: addFragment(CharacterDetailFragment(), R.id.secondPane)
        }
        showFromStack(CharacterListFragment::class.java.simpleName)
            ?: addFragment(CharacterListFragment.newInstance(), R.id.firstPane)

        getViewModel().characterDetailState.observe(this, {
            if (!getViewModel().isDualPane)
                replaceFragment(CharacterDetailFragment.newInstance(it), R.id.firstPane, true)
        })
    }

    override fun createViewModelFactory(): HomeViewModel {
        val homeViewModel by viewModels<HomeViewModel>()
        return homeViewModel
    }

    override fun getRootView() = binding.root
}