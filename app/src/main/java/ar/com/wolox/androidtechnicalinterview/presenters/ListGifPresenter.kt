package ar.com.wolox.androidtechnicalinterview.presenters

import ar.com.wolox.androidtechnicalinterview.interfaces.APIServices
import ar.com.wolox.androidtechnicalinterview.interfaces.ListGifContract
import ar.com.wolox.androidtechnicalinterview.models.Gif
import com.orm.SugarRecord
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ListGifPresenter(
        val view: ListGifContract.View
) : ListGifContract.Presenter {

    private val apiServices by lazy {
        APIServices.create()
    }
    private var disposable: Disposable? = null

    override fun search(query: String) {
        disposable = apiServices
                .searchGifs(query)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            view.showGifs(result.data)
                        },
                        { error ->
                            error.printStackTrace()
                            view.showError(error.localizedMessage)
                        }
                )
    }

    override fun saveFavorite(gif: Gif) {
        SugarRecord.save(gif)
        SugarRecord.save(gif.images?.fixedHeightSmall)
        SugarRecord.save(gif.images)
        view.addedSuccessfully()
    }

    override fun deleteFavorite(gif: Gif) {
        SugarRecord.delete(gif)
        view.deletedSuccessfully()
    }

    override fun getFavorites(query: String?) {
        if (query==null) {
            view.showGifs(Gif.listAll(true))
        } else {
            view.showGifs(SugarRecord.find(Gif::class.java,"title like ?", "%$query%"))
        }
    }

}