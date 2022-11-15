package com.f55121082.mysqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.f55121082.mysqlite.databinding.ActivityMainBinding;

import utils.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);

        binding.btnLogin.setOnClickListener(view -> {
            Intent LoginIntent = new
                    Intent(RegisterActivity.this, MainActivity.class);
            startActivity(LoginIntent);
        });

        binding.btnRegister.setOnClickListener(view -> {
            String username =
                    binding.edtUsername.getText().toString().trim();
            String password =
                    binding.edtPassword.getText().toString().trim();
            String confPass =
                    binding.edtConfirm.getText().toString().trim();

            if (password.equals(confPass)) {
                long val = databaseHelper.addUser(username,
                        password);
                if (val > 0) {
                    Toast.makeText(RegisterActivity.this,"You have Register",
                            Toast.LENGTH_SHORT).show();
                    Intent moveToLogin = new
                            Intent(RegisterActivity.this, MainActivity.class);
                    startActivity(moveToLogin);
                }
            } else {
                Toast.makeText(RegisterActivity.this,"Password is Not Matching", Toast.LENGTH_SHORT).show();
            }
        });

    }
}