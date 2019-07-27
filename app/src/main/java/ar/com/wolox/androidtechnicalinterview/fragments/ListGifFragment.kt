package ar.com.wolox.androidtechnicalinterview.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import ar.com.wolox.androidtechnicalinterview.R
import ar.com.wolox.androidtechnicalinterview.adapters.GifRecyclerAdapter
import ar.com.wolox.androidtechnicalinterview.enums.GifType
import ar.com.wolox.androidtechnicalinterview.interfaces.ListGifContract
import ar.com.wolox.androidtechnicalinterview.models.Gif
import ar.com.wolox.androidtechnicalinterview.presenters.GifRowPresenter
import ar.com.wolox.androidtechnicalinterview.presenters.ListGifPresenter
import ar.com.wolox.androidtechnicalinterview.utils.CustomProgressBar
import kotlinx.android.synthetic.main.fragment_list_gif.*

class ListGifFragment : Fragment(), ListGifContract.View, GifRecyclerAdapter.OnItemClickListener {

    companion object {
        private const val ARGUMENT_TYPE = "argument_type"
        fun newInstance(type: GifType): ListGifFragment {
            val fragment = ListGifFragment()
            val args = Bundle()
            args.putSerializable(ARGUMENT_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }
    // Attributes
    private lateinit var presenter: ListGifContract.Presenter
    private lateinit var progressBar: CustomProgressBar

    override fun onCreateView(inflater: LayoutInflater, viewGroup: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_gif, viewGroup, false)
        initialize()
        loadData()
        return view
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        progressBar.hide()
    }

    override fun showGifs(gifs: List<Gif>) {
        val presenter = GifRowPresenter(gifs)
        val adapter = GifRecyclerAdapter(presenter, this)
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        recyclerView.adapter = adapter
        progressBar.hide()
    }

    override fun onItemClick(url: String) {
        share(url)
    }

    // Private methods
    private fun initialize() {
        presenter = ListGifPresenter(this)
        progressBar = CustomProgressBar(activity!!)
    }

    private fun share(text: String) {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, getString(R.string.general_share)))
    }

    // Public methods
    fun loadData(query: String = "random") {
        progressBar.show()
        presenter.search(query)
    }

}