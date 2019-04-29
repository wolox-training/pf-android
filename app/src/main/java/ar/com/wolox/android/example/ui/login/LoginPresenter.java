package ar.com.wolox.android.example.ui.login;

import java.util.List;

import javax.inject.Inject;

import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.network.UserService;
import ar.com.wolox.android.example.utils.UserSession;
import ar.com.wolox.wolmo.core.presenter.BasePresenter;
import ar.com.wolox.wolmo.networking.retrofit.RetrofitServices;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 */

public class LoginPresenter extends BasePresenter<ILoginView> {

    private UserSession mUserSession;
    private RetrofitServices mRetrofitServices;

    @Inject
    public LoginPresenter(RetrofitServices mRetrofitServices) {
        this.mRetrofitServices = mRetrofitServices;
    }

    public LoginPresenter(UserSession mUserSession) {
        this.mUserSession = mUserSession;
    }

    /**
     *
     * @param mail mail ingresado por el usuario
     */
    public void validarUserMail(String mail) {
        mRetrofitServices.getService(UserService.class).getUserByMail(mail).enqueue(new Callback<List<User>>() {

            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.body().isEmpty()) {
                    getView().onUsernameValidated(null);
                } else {
                    getView().onUsernameValidated(response.body().get(0));
                }
                response.body();
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                getView().onUsernameValidated(null);
            }
        });
    }

    public void storeUsername(String text) {
        mUserSession.setUsername(text);
        getView().onUsernameSaved();
    }
}
