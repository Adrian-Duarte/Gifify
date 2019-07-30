package ar.com.wolox.androidtechnicalinterview.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ar.com.wolox.androidtechnicalinterview.holders.GifRowViewHolder
import ar.com.wolox.androidtechnicalinterview.R
import ar.com.wolox.androidtechnicalinterview.models.Gif
import ar.com.wolox.androidtechnicalinterview.presenters.GifRowPresenter

class GifRecyclerAdapter(
        private val presenter: GifRowPresenter,
        private val listener: OnItemClickListener
) : RecyclerView.Adapter<GifRowViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(url: String)
        fun onItemLongClick(gif: Gif)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifRowViewHolder {
        return GifRowViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.row_gif,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return presenter.getGifsCount()
    }

    override fun onBindViewHolder(holder: GifRowViewHolder, position: Int) {
        presenter.onBindGifRowViewAtPosition(position, holder)
        holder.itemView.setOnClickListener({
            listener.onItemClick(presenter.getCurrentGif(position).url)
        })
        holder.itemView.setOnLongClickListener({
            listener.onItemLongClick(presenter.getCurrentGif(position))
            true
        })
    }

}