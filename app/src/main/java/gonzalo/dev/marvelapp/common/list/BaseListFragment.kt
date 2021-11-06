package gonzalo.dev.marvelapp.common.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import gonzalo.dev.marvelapp.databinding.ActivityBaseListBinding

/**
 * @param DS The data set type.
 * @param VM The view model type.
 */
abstract class BaseListFragment<DS> : Fragment(), ICardClick<DS> {

    private val binding by lazy { ActivityBaseListBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        binding.baseList.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            addOnScrollListener(object : PaginationScrollListener() {
                override fun loadMoreItems() {
                    loadNextPage()
                    binding.progressBar.visibility = View.VISIBLE
                }
            })
        }

        binding.baseRefresh.setOnRefreshListener {
            setRefreshing(true)
            onSwipe()
        }
    }

    protected fun canRefresh(value: Boolean) {
        binding.baseRefresh.isEnabled = value
    }

    protected fun dataSetReady() {
        binding.progressBar.visibility = View.GONE
        setRefreshing(false)
    }

    protected fun getList() = binding.baseList

    /**
     * Show the swipe progress animation depending on the boolean value received.
     * @param value true or false in order to show or hide the progress view.
     */
    internal fun setRefreshing(value: Boolean) {
        binding.baseRefresh.isRefreshing = value
    }

    abstract fun onSwipe()

    abstract fun loadNextPage()
}