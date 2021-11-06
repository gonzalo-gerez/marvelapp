package gonzalo.dev.marvelapp.home

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.activityViewModels
import gonzalo.dev.core.domain.model.Character
import gonzalo.dev.marvelapp.R
import gonzalo.dev.marvelapp.common.list.BaseListFragment
import gonzalo.dev.marvelapp.image.ImageActivity

class CharacterListFragment : BaseListFragment<Character>() {

    companion object {
        fun newInstance() = CharacterListFragment()
    }

    private val viewModel: HomeViewModel by activityViewModels()
    private val adapter = CharacterAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.paginationState.observe(viewLifecycleOwner, {
            dataSetReady()
            getList().adapter?.notifyDataSetChanged()
        })

        viewModel.characterState.observe(viewLifecycleOwner, { dataSet ->
            dataSetReady()
            getList().adapter = adapter.also { it.setData(dataSet) }
        })

        viewModel.fetchCharacters()
    }

    override fun onSwipe() {
        viewModel.refreshList()
    }

    override fun onItemClicked(item: Character) {
        viewModel.showDetailFragment(item)
    }

    override fun onItemAction(item: Character, view: View) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            activity as Activity, view, getString(R.string.thumb_transition)
        )

        startActivity(Intent(context, ImageActivity::class.java).apply {
            putExtra(ImageActivity.EXTRA, "${item.thumbnail.path}.${item.thumbnail.extension}")
        }, options.toBundle())
    }

    override fun loadNextPage() {
        viewModel.loadMore()
    }

}