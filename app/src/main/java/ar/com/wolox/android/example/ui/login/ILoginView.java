package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.example.model.User;

/**
 *
 */
public interface ILoginView {

    void onUsernameSaved();
    void onUsernameValidated(User user);
}
