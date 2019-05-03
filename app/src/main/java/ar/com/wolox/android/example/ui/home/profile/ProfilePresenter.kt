package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.wolmo.core.presenter.BasePresenter
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices
import javax.inject.Inject

class ProfilePresenter @Inject constructor(
    private val mRetrofitServices: RetrofitServices
) : BasePresenter<IProfileView>() {

    override fun onViewAttached() {
    }

    companion object {
        private const val POST_ID = 1
    }
}