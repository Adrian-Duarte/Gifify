package ar.com.wolox.androidtechnicalinterview.interfaces

import ar.com.wolox.androidtechnicalinterview.models.Gif

interface ListGifContract {

    interface View {
        fun addedSuccessfully()
        fun deletedSuccessfully()
        fun showError(message: String)
        fun showGifs(gifs: List<Gif>)
    }

    interface Presenter {
        fun deleteFavorite(gif: Gif)
        fun getFavorites(query: String?)
        fun saveFavorite(gif: Gif)
        fun search(query: String)
    }

}