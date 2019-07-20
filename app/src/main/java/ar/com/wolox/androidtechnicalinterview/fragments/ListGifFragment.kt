package ar.com.wolox.androidtechnicalinterview.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ar.com.wolox.androidtechnicalinterview.R
import ar.com.wolox.androidtechnicalinterview.enums.GifType
import kotlinx.android.synthetic.main.fragment_list_gif.view.*

class ListGifFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater, viewGroup: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list_gif, viewGroup, false)

        val args = arguments?.getSerializable(ARGUMENT_TYPE) as GifType
        args.let {
            view.tvTitle.text = args.name
        }

        return view
    }

}