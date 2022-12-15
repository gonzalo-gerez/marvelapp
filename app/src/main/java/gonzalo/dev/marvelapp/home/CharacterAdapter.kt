package gonzalo.dev.marvelapp.home

import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.view.SimpleDraweeView
import gonzalo.dev.core.domain.model.CharacterDataModel
import gonzalo.dev.core.domain.model.CharacterModel
import gonzalo.dev.marvelapp.R
import gonzalo.dev.marvelapp.common.list.BaseAdapter
import gonzalo.dev.marvelapp.common.list.ICardClick
import gonzalo.dev.marvelapp.common.util.FrescoUtils

class CharacterAdapter(private val callback: ICardClick<CharacterDataModel>) :
    BaseAdapter<CharacterDataModel, CharacterAdapter.PostViewHolder>() {

    private var selectedPosition = RecyclerView.NO_POSITION

    override fun setData(dataSet: ArrayList<CharacterDataModel>) {
        if (_data.isNotEmpty()) _data.addAll(dataSet) else _data = dataSet
        notifyDataSetChanged()
    }

    override fun getItemResource(): Int {
        return R.layout.item_list_post
    }

    override fun getViewHolderInstance(view: View): PostViewHolder {
        return PostViewHolder(view)
    }

    override fun onBind(holder: PostViewHolder, position: Int) {
        holder.item.isSelected = selectedPosition == holder.adapterPosition
        holder.bind(_data[holder.adapterPosition])
    }

    inner class PostViewHolder(val item: View) : RecyclerView.ViewHolder(item) {

        /** Context for resources **/
        private val context = item.context

        private val title: TextView by lazy { item.findViewById(R.id.itemTitle) }
        private val body: TextView by lazy { item.findViewById(R.id.itemBodyText) }
        private val thumb: SimpleDraweeView by lazy { item.findViewById(R.id.itemThumb) }
        private val dismiss: Button by lazy { item.findViewById(R.id.itemDismiss) }
        private val viewIndicator: ImageView by lazy { item.findViewById(R.id.viewIdicator) }

        fun bind(character: CharacterDataModel) {
            title.text = character.name
            body.text = character.description
            FrescoUtils.setImage(
                thumb,
                "${character.thumbnail.path}.${character.thumbnail.extension}"
            )

            if (character.clicked) viewIndicator.visibility =
                View.GONE else viewIndicator.visibility =
                View.VISIBLE

            dismiss.setOnClickListener {
                item.startAnimation(
                    AnimationUtils
                        .loadAnimation(context, R.anim.design_trans_slide_out_to_left)
                )

                if (adapterPosition != RecyclerView.NO_POSITION) {
                    _data.removeAt(adapterPosition)
                    notifyItemRemoved(adapterPosition)
                }
            }

            item.setOnClickListener {
                character.clicked = true
                viewIndicator.visibility = View.GONE
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)
                callback.onItemClicked(character)
            }

            thumb.setOnClickListener {
                callback.onItemAction(character, thumb)
            }
        }
    }

}
