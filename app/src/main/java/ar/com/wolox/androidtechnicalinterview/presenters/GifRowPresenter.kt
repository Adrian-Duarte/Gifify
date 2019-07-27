package ar.com.wolox.androidtechnicalinterview.presenters

import ar.com.wolox.androidtechnicalinterview.GifRowView
import ar.com.wolox.androidtechnicalinterview.models.Gif

class GifRowPresenter(val gifs: List<Gif>) {

    fun onBindGifRowViewAtPosition(position: Int, rowView: GifRowView) {
        val gif = gifs[position]
        rowView.setTitle(gif.title)
    }

    fun getGifsCount() : Int {
        return gifs.size
    }

    fun getCurrentGif(position: Int) : Gif {
        return gifs[position]
    }

}