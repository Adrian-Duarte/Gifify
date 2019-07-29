package ar.com.wolox.androidtechnicalinterview

import android.support.v7.widget.RecyclerView
import android.view.View
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.row_gif.view.*


class GifRowViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), GifRowView {

    override fun setImage(image: String) {
        Glide
                .with(itemView)
                .load(image)
                .into(itemView.ivImage)
    }

    override fun setTitle(title: String) {
        itemView.tvTitle.text = title
    }

}