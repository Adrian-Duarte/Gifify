package ar.com.wolox.androidtechnicalinterview.fragments

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
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
    private var type: GifType? = null

    override fun onCreateView(inflater: LayoutInflater, viewGroup: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_gif, viewGroup, false)
        initialize()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadData()
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        type.run {
            if (type == GifType.FAVORITES && isVisibleToUser) {
                loadData()
            }
        }
    }

    override fun showError(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        progressBar.hide()
    }

    override fun showGifs(gifs: List<Gif>) {
        val presenter = GifRowPresenter(gifs)
        val adapter = GifRecyclerAdapter(presenter, this)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter
        progressBar.hide()
    }

    override fun onItemClick(url: String) {
        share(url)
    }

    override fun onItemLongClick(gif: Gif) {
        when(type) {
            GifType.RANDOM -> {
                presenter.saveFavorite(gif)
            }
            GifType.FAVORITES -> {
                showConfirmDialog(gif)
            }
        }
    }

    override fun addedSuccessfully() {
        Toast.makeText(context, getString(R.string.general_added_successfully), Toast.LENGTH_LONG).show()
    }

    override fun deletedSuccessfully() {
        Toast.makeText(context, getString(R.string.general_deleted_successfully), Toast.LENGTH_LONG).show()
        presenter.getFavorites(null)
    }

    // Private methods
    private fun initialize() {
        presenter = ListGifPresenter(this)
        progressBar = CustomProgressBar(activity!!)
        val args = arguments?.getSerializable(ARGUMENT_TYPE) as GifType
        args.run {
            type = args
        }
    }

    private fun share(text: String) {
        val intent = Intent(android.content.Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, getString(R.string.share_subject))
        intent.putExtra(android.content.Intent.EXTRA_TEXT, text)
        startActivity(Intent.createChooser(intent, getString(R.string.general_share)))
    }

    private fun showConfirmDialog(gif: Gif) {
        AlertDialog.Builder(context!!)
                .setTitle(getString(R.string.dialog_title))
                .setMessage(getString(R.string.dialog_message))
                .setPositiveButton(getString(R.string.general_yes), { _, _ -> presenter.deleteFavorite(gif) })
                .setNegativeButton(getString(R.string.general_no), null)
                .show()
    }

    // Public methods

    fun loadData(query: String? = null) {
        when(type) {
            GifType.RANDOM -> {
                progressBar.show()
                presenter.search(query ?: "random")
            }
            GifType.FAVORITES -> {
                presenter.getFavorites(query)
            }
        }
    }

}