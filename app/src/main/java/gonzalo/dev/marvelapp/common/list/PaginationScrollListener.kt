package gonzalo.dev.marvelapp.common.list

import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener() : RecyclerView.OnScrollListener() {
    override fun onScrolled(@NonNull recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            if (!recyclerView.canScrollVertically(1)) {
                loadMoreItems()
            }
        }
    }

    abstract fun loadMoreItems()
}