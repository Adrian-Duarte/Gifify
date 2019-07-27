package ar.com.wolox.androidtechnicalinterview.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.util.SparseArray
import android.view.ViewGroup
import ar.com.wolox.androidtechnicalinterview.enums.GifType
import ar.com.wolox.androidtechnicalinterview.fragments.ListGifFragment


class GifPageAdapter(fragmentManager: FragmentManager, private val numberOfTabs: Int) :
        FragmentPagerAdapter(fragmentManager) {

    // Attributes
    var registeredFragments = SparseArray<Fragment>()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val fragment = super.instantiateItem(container, position) as Fragment
        registeredFragments.put(position, fragment)
        return fragment
    }

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ListGifFragment.newInstance(GifType.RANDOM)
        1 -> ListGifFragment.newInstance(GifType.FAVORITES)
        else -> ListGifFragment.newInstance(GifType.RANDOM)
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        registeredFragments.remove(position)
        super.destroyItem(container, position, `object`)
    }

    override fun getCount(): Int {
        return numberOfTabs
    }

    // Public methods
    fun getRegisteredFragment(position: Int): Fragment {
        return registeredFragments.get(position)
    }

}