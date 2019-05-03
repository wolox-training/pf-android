package ar.com.wolox.android.example.ui.home.news

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class NewsPresenter @Inject constructor(
    private val toastFactory: ToastFactory
) : BasePresenter<INewsView>() {

    override fun onViewAttached() {
        toastFactory.show("We don't show toasts from presenters")
    }
}