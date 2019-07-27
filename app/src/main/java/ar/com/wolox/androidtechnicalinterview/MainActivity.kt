package ar.com.wolox.androidtechnicalinterview

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.TabLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SearchView
import android.view.Menu
import ar.com.wolox.androidtechnicalinterview.adapters.GifPageAdapter
import ar.com.wolox.androidtechnicalinterview.fragments.ListGifFragment
import kotlinx.android.synthetic.main.activity_main.*

/**
 * MIT License
 *
 * Copyright (c) 2019 Wolox S.A
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software
 * and associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 *
 */

class MainActivity : AppCompatActivity() {

    // Attributes
    private lateinit var tabsAdapter:  GifPageAdapter
    private var searchQuery = ""
    private var handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeUIElements()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu, menu)
        val menuSearchItem = menu?.findItem(R.id.action_search)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menuSearchItem?.actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }
            override fun onQueryTextChange(nextText: String): Boolean {
                searchQuery = nextText
                handler.removeCallbacksAndMessages(null)
                handler.postDelayed({
                    if (nextText.isEmpty()) getCurrentFragment().loadData() else getCurrentFragment().loadData(nextText)
                }, 400)
                return false
            }
        })
        searchView.maxWidth = Integer.MAX_VALUE
        return true
    }

    // Private methods
    private fun initializeUIElements() {
        initializeToolbar()
        initializeViewPager()
        initializeTabLayout()
    }

    private fun initializeTabLayout() {
        tabLayout.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun initializeToolbar() {
        setSupportActionBar(toolbar)
    }

    private fun initializeViewPager() {
        tabsAdapter = GifPageAdapter(supportFragmentManager, tabLayout.tabCount)
        viewPager.adapter = tabsAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
    }

    private fun getCurrentFragment() : ListGifFragment {
        return tabsAdapter.getRegisteredFragment(viewPager.currentItem) as ListGifFragment
    }

}
