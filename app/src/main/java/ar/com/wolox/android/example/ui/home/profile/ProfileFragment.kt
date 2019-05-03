package ar.com.wolox.android.example.ui.home.profile

import ar.com.wolox.android.R
import ar.com.wolox.wolmo.core.fragment.WolmoFragment
import ar.com.wolox.wolmo.core.util.ToastFactory
import javax.inject.Inject

class ProfileFragment @Inject constructor() : WolmoFragment<ProfilePresenter>(), IProfileView {

    @Inject
    internal lateinit var mToastFactory: ToastFactory

    override fun layout(): Int = R.layout.fragment_profile

    override fun init() {
//        vToolbar.title = "Profile"
    }
}