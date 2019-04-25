package ar.com.wolox.android.example.ui.login;

import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ar.com.wolox.android.R;
import ar.com.wolox.android.example.ui.example.ExamplePresenter;
import ar.com.wolox.android.example.ui.viewpager.ViewPagerActivity;
import ar.com.wolox.wolmo.core.fragment.WolmoFragment;
import butterknife.BindView;

/**
 * sarasa
 */

public class LoginFragment extends WolmoFragment<ExamplePresenter> implements ILoginView {

    @BindView(R.id.vLoginButton)
    Button vLoginButton;
    @BindView(R.id.vSignUpButton)
    Button vSignUpButton;
    @BindView(R.id.vUserNameInput)
    EditText vUserNameInput;
    @BindView(R.id.vPasswordInput)
    EditText vPasswordIntro;
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
                Toast.makeText(getContext(), "Log In press", Toast.LENGTH_LONG).show();
                //getPresenter().storeUsername(vUserNameInput.getText().toString());
            }
        });

        vSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Sign In press", Toast.LENGTH_LONG).show();
            }
        });

        vTermsAndConditions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onUsernameSaved() {
        Intent intent = new Intent(getActivity(), ViewPagerActivity.class);
        startActivity(intent);
    }
}
