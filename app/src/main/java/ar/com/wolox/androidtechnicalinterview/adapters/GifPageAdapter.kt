package ar.com.wolox.androidtechnicalinterview.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import ar.com.wolox.androidtechnicalinterview.enums.GifType
import ar.com.wolox.androidtechnicalinterview.fragments.ListGifFragment

class GifPageAdapter(fragmentManager: FragmentManager, private val numberOfTabs: Int) :
        FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ListGifFragment.newInstance(GifType.RANDOM)
        1 -> ListGifFragment.newInstance(GifType.FAVORITES)
        else -> ListGifFragment.newInstance(GifType.RANDOM)
    }

    override fun getCount(): Int {
        return numberOfTabs
    }

}