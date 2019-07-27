package ar.com.wolox.androidtechnicalinterview.interfaces

import ar.com.wolox.androidtechnicalinterview.models.Gif

interface ListGifContract {

    interface View {
        fun showError(message: String)
        fun showGifs(gifs: List<Gif>)
    }

    interface Presenter {
        fun search(query: String)
    }

}