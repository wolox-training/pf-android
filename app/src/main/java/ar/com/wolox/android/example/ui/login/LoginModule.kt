package ar.com.wolox.android.example.ui.login

import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class LoginModule {

    @ContributesAndroidInjector
    internal abstract fun loginActivity(): LoginActivity

    @ContributesAndroidInjector
    internal abstract fun routeActivity(): RouteActivity

    @ContributesAndroidInjector
    internal abstract fun signInActivity(): SignInActivity

    @ContributesAndroidInjector
    internal abstract fun loginFragment(): LoginFragment

    @ContributesAndroidInjector
    internal abstract fun signInFragment(): SignInFragment
}
