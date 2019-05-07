package ar.com.wolox.android.example.ui.home

import android.graphics.Color
import android.support.v4.app.Fragment
import android.support.v4.util.Pair
import ar.com.wolox.android.R
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import ar.com.wolox.wolmo.core.adapter.viewpager.SimpleFragmentPagerAdapter
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.presenter.BasePresenter
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_viewpager.vViewPager
import javax.inject.Inject

class HomeFragment @Inject constructor() : WolmoFragment<BasePresenter<Any>>() {

    private lateinit var fragmentPagerAdapter: SimpleFragmentPagerAdapter

    override fun layout(): Int = R.layout.fragment_home

    override fun init() {

        fragmentPagerAdapter = SimpleFragmentPagerAdapter(childFragmentManager)
        fragmentPagerAdapter.addFragments(
                Pair<Fragment, String>(NewsFragment.newInstance(), getString(R.string.news)),
                Pair<Fragment, String>(ProfileFragment.newInstance(), getString(R.string.profile)))
        vViewPager.adapter = fragmentPagerAdapter

        vTabs.setupWithViewPager(vViewPager)

        vTabs.setTabTextColors(Color.parseColor("#7E7E7E"), Color.parseColor("#8DC63F"))
        vTabs.getTabAt(0)?.setIcon(R.drawable.ic_news_on_off)
        vTabs.getTabAt(1)?.setIcon(R.drawable.ic_profile_on_off)
    }
}