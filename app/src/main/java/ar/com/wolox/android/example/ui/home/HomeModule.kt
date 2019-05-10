package ar.com.wolox.android.example.ui.home

import ar.com.wolox.android.example.ui.home.news.NewsDetailActivity
import ar.com.wolox.android.example.ui.home.news.NewsDetailFragment
import ar.com.wolox.android.example.ui.home.news.NewsFragment
import ar.com.wolox.android.example.ui.home.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeModule {

    @ContributesAndroidInjector
    internal abstract fun homeActivity(): HomeActivity

    @ContributesAndroidInjector
    internal abstract fun homeFragment(): HomeFragment

    @ContributesAndroidInjector
    internal abstract fun newsFragment(): NewsFragment

    @ContributesAndroidInjector
    internal abstract fun profileFragment(): ProfileFragment

    @ContributesAndroidInjector
    internal abstract fun newsDetailActivity(): NewsDetailActivity

    @ContributesAndroidInjector
    internal abstract fun detailFragment(): NewsDetailFragment
}
