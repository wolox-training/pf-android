package ar.com.wolox.android.example.ui.login;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;

/**
 *
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private UserSession mUserSession;

    @Inject
    public LoginPresenter(UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }

    public void storeUsername(Context context, String userName) {
        SharedPreferences sharedPref = context.getSharedPreferences(
                context.getString(R.string.preference_file_key), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(context.getString(R.string.saved_user_email), userName);
        editor.commit();
        getView().onUsernameSaved();
    }
}
