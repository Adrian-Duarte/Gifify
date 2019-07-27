package ar.com.wolox.androidtechnicalinterview.presenters

import ar.com.wolox.androidtechnicalinterview.interfaces.APIServices
import ar.com.wolox.androidtechnicalinterview.interfaces.ListGifContract
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListGifPresenter(
        val view: ListGifContract.View
) : ListGifContract.Presenter {

    val apiServices by lazy {
        APIServices.create()
    }
    var disposable: Disposable? = null

    override fun search(query: String) {
        disposable = apiServices
                .searchGifs(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result -> view.showGifs(result.data) },
                        { error ->
                            error.printStackTrace()
                            view.showError("xxxx")
                        }
                )
    }


}