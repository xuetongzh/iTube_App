package com.google.itubeapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.itubeapp.bean.UserBean;

public class SignUpActivity extends AppCompatActivity {
    public EditText edit_full_name_login;
    public EditText edit_username_login;
    public EditText edit_password_login;
    public EditText edit_confirm_password_login;
    public Button btn_create_account_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));  //Set status bar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  //Implement the status bar icon and text color as dark
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        // Get instance of the UI elements
        edit_full_name_login = findViewById(R.id.edit_full_name_login);
        edit_username_login = findViewById(R.id.edit_username_login);
        edit_password_login = findViewById(R.id.edit_password_login);
        edit_confirm_password_login = findViewById(R.id.edit_confirm_password_login);
        btn_create_account_login = findViewById(R.id.btn_create_account_login);

        btn_create_account_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if the entered password and confirm password match
                if (!edit_password_login.getText().toString().equals(edit_confirm_password_login.getText().toString())) {
                    Toast.makeText(SignUpActivity.this, "The entered passwords do not match.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Create a new UserBean object and save user information
                if (new UserBean(edit_full_name_login.getText().toString(), edit_username_login.getText().toString(), edit_password_login.getText().toString()).save()) {
                    startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                }
            }
        });

    }


}