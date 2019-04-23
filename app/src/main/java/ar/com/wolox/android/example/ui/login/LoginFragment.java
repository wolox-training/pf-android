package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
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
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity;
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
                    getPresenter().storeUsername(getActivity(), vUserNameInput.getText().toString());
                }
            }
        });

        vSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vUserNameInput.requestFocus();
                Toast.makeText(getContext(), "Sign In press", Toast.LENGTH_LONG).show();
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
        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
        startActivity(intent);
    }
}
