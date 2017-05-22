package br.com.doublef.pipedriveclient.feature.login.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import javax.inject.Inject;

import br.com.doublef.pipedriveclient.R;
import br.com.doublef.pipedriveclient.application.App;
import br.com.doublef.pipedriveclient.feature.home.view.ContactsListActivity;
import br.com.doublef.pipedriveclient.feature.login.contract.LoginContract;
import br.com.doublef.pipedriveclient.feature.login.presenter.LoginPresenterImpl;
import br.com.doublef.pipedriveclient.model.ResponseUserData;
import br.com.doublef.pipedriveclient.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private static final long ANIMATION_DURATION = 4500;

    @Inject
    LoginPresenterImpl loginPresenter;

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    TextInputEditText password;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.login_component)
    LinearLayout loginComponent;

    public static void start(Context context) {
        Intent starter = new Intent(context, LoginActivity.class);
        starter.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        ((App) getApplication()).components().inject(this);
        loginPresenter.attachView(this);

        loginPresenter.attemptLogin();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    public void onClickLogin(View view) {
        User user = createUserFromView();
        loginPresenter.login(user);
    }

    private User createUserFromView() {
        String userName = username.getText().toString();
        String password = this.password.getText().toString();
        return new User(userName, password);
    }

    @Override
    public void login() {
        loginComponent.setVisibility(View.VISIBLE);

        YoYo.with(Techniques.FadeInUp)
                .duration(ANIMATION_DURATION)
                .playOn(loginComponent);

    }

    @Override
    public void autoLogin() {
        ContactsListActivity.start(this);
    }

    @Override
    public void onLoginSuccess(ResponseUserData personData) {
        ContactsListActivity.start(this);
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void enableLoginButton() {
        login.setClickable(true);
        login.setEnabled(true);
    }

    @Override
    public void disableLoginButton() {
        login.setClickable(false);
        login.setEnabled(false);
    }
}
