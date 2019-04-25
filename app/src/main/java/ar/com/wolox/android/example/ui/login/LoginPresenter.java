package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 *
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private UserSession mUserSession;

    public LoginPresenter(UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }

    public void storeUsername(String text) {
        mUserSession.setUsername(text);
        getView().onUsernameSaved();
    }
}
