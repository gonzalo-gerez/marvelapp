package gonzalo.dev.marvelapp.common.list

import android.view.View

interface ICardClick<Any> {

    fun onItemClicked(item: Any)
    fun onItemAction(item: Any, view: View) {
    }

}
