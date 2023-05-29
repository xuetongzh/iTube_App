package com.google.itubeapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.itubeapp.bean.UserBean;
import com.permissionx.guolindev.PermissionX;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    public Button btn_login;
    public Button btn_singUp;
    public EditText edit_username;
    public EditText edit_password;
    private UserBean userBean;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setStatusBarColor(getResources().getColor(R.color.white));  //Set status bar color
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);  //Implement the status bar icon and text color as dark
        ActionBar actionBar = getSupportActionBar();  // Hide the ActionBar
        if (actionBar != null) {
            actionBar.hide();
        }

        // Login button click event
        btn_login = findViewById(R.id.btn_login);
        btn_singUp = findViewById(R.id.btn_signUp);
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            @SuppressLint("Range")
            public void onClick(View view) {
                // Check if the user exists
                if (LitePal.where("username = ?", edit_username.getText().toString()).find(UserBean.class).size() == 0) {
                    Toast.makeText(MainActivity.this, "The user does not exist, please complete the sign up first.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Retrieve user information
                userBean = LitePal.where("username = ?", edit_username.getText().toString()).find(UserBean.class).get(0);

                String password = userBean.getPasssword();
                // Check if the entered password is correct
                if (password.equals(edit_password.getText().toString())) {
                    // If the password is correct, navigate to HomeActivity and pass the username information
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    intent.putExtra("username", edit_username.getText().toString());
                    startActivity(intent);
                } else {
                    // If the password is incorrect, show an error message
                    Toast.makeText(MainActivity.this, "Wrong password, please try again.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Sign up button click event
        btn_singUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to SignUpActivity
                Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        // Initialize permissions
        initPermissions();
    }

    private void initPermissions() {
        PermissionX.init(this)
                .permissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                .request((allGranted, grantedList, deniedList) -> {
                    if (!allGranted) {
                        // If any permissions are denied, show a prompt message
                        Toast.makeText(this, "The following permissions are forbidden to run" + grantedList + "Please restart the app after allowed", Toast.LENGTH_LONG).show();
                    }
                });
    }
}