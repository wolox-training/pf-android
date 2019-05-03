package ar.com.wolox.android.example.ui.login;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.ui.example.ExamplePresenter;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;

/**
 * sarasa
 */

public class SignInFragment extends WolmoFragment<ExamplePresenter> implements ILoginView {

    @Override
    public int layout() {
        return R.layout.fragment_signin;
    }

    @Override
    public void init() {
    }

    @Override
    public void onUsernameSaved() {
    }

    @Override
    public void onUsernameValidated(User user) {

    }
}
