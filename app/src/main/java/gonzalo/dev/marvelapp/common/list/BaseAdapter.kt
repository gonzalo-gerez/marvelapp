package gonzalo.dev.marvelapp.common.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @param T The data set type data.
 * @param VH The ViewHolder type data.
 */
abstract class BaseAdapter<T, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    private val dataSetCopy = ArrayList<T>()

    internal var _data = ArrayList<T>()
    var canClick = true

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(getItemResource(), parent, false)
        return getViewHolderInstance(view)
    }

    override fun getItemCount(): Int {
        return _data.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        onBind(holder, position)
    }

    abstract fun setData(dataSet: ArrayList<T>)

    abstract fun getItemResource(): Int

    abstract fun getViewHolderInstance(view: View): VH

    abstract fun onBind(holder: VH, position: Int)
}