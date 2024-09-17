package com.longthph30891.note_mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.longthph30891.note_mvp.R;
import com.longthph30891.note_mvp.databinding.ActivityLoginBinding;
import com.longthph30891.note_mvp.presenter.LoginPresenter;
import com.longthph30891.note_mvp.view.interfaceView.LoginInterface;

public class LoginActivity extends AppCompatActivity implements LoginInterface {
    private ActivityLoginBinding binding;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loginPresenter = new LoginPresenter(this);
        binding.btnLogin.setOnClickListener(view -> {
            isLogin();
        });
    }

    private void isLogin() {
        String uername = binding.edUsername.getText().toString().trim();
        String password = binding.edPassword.getText().toString().trim();
        loginPresenter.login(this,uername,password);
    }

    @Override
    public void loginSuccess() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void loginFailure() {
        Toast.makeText(this, "Username or Password invalid", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void isValidUsername() {
        binding.tilUsername.setError("Please enter a username");
    }

    @Override
    public void isValidPassword() {
        binding.tilPassword.setError("Please enter a password");
    }

    @Override
    public void clearSetErrorUsername() {
        binding.tilUsername.setError(null);
    }

    @Override
    public void clearSetErrorPassword() {
        binding.tilPassword.setError(null);
    }
}