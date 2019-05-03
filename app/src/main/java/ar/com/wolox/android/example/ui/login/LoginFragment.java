package ar.com.wolox.android.example.ui.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.model.User;
import ar.com.wolox.android.example.ui.home.HomeActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

/**
 * sarasa
 */

public class LoginFragment extends WolmoFragment<LoginPresenter> implements ILoginView {

    @BindView(R.id.vLoginButton)
    Button vLoginButton;
    @BindView(R.id.vSignUpButton)
    Button vSignUpButton;
    @BindView(R.id.vUserNameInput)
    EditText vUserNameInput;
    @BindView(R.id.vPasswordInput)
    EditText vPasswordInput;
    @BindView(R.id.vTermsAndConditions)
    TextView vTermsAndConditions;

    private ProgressDialog progressDialog;

    @Override
    public int layout() {
        return R.layout.fragment_login;
    }

    @Override
    public void init() {
        vUserNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                vLoginButton.setEnabled(s.length() != 0);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        vLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidUsernameAndPassword(vUserNameInput.getText().toString())) {
                    vUserNameInput.setError(getString(R.string.error_campo_incompleto));
                } else if (!isValidEmail(vUserNameInput.getText().toString())) {
                    vUserNameInput.setError(getString(R.string.error_formato_invalido));
                } else if (vPasswordInput.getText().toString().isEmpty()) {
                    vPasswordInput.setError(getString(R.string.error_campo_incompleto));
                } else {
                    progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage(getString(R.string.loading));
                    progressDialog.setIndeterminate(true);
                    progressDialog.show();
                    getPresenter().validarUserMail(vUserNameInput.getText().toString());
                }
            }
        });

        vSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SignInActivity.class);
                startActivity(intent);
            }
        });

        vTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private boolean isValidUsernameAndPassword(String userName) {
        return TextUtils.isEmpty(userName);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    @Override
    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    @Override
    public void onUsernameValidated(User user) {
        progressDialog.dismiss();
        if (user != null) {
            if (user.getPassword().equals(vPasswordInput.getText().toString())) {
                getPresenter().storeUsername(getActivity(), user.getUsername());
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                Toast.makeText(getContext(), getString(R.string.wrong_password), Toast.LENGTH_LONG).show();
            }
        } else if (isNetworkAvailable(getContext())) {
            Toast.makeText(getContext(), getString(R.string.user_not_found), Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getContext(), getString(R.string.no_internet_conection), Toast.LENGTH_LONG).show();
        }
    }

    public boolean isNetworkAvailable(Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
