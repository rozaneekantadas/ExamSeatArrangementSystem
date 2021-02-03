package com.suptodas.diu.examseatarrangementsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    Button login;
    TextInputLayout usernameLayout, passLayout;
    TextInputEditText username, pass;
    String uName, uPass;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login = findViewById(R.id.logIn);
        usernameLayout = findViewById(R.id.userNameLayout);
        passLayout = findViewById(R.id.passLayout);
        username = findViewById(R.id.userName);
        pass = findViewById(R.id.pass);

        toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View customView = getLayoutInflater().inflate(R.layout.login_alert_dialog, null);
        builder.setView(customView);
        final AlertDialog dialog = builder.create();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uName = username.getText().toString();
                uPass = pass.getText().toString();

                if(uName.isEmpty() || uPass.isEmpty()){
                    if(uName.isEmpty()){
                        usernameLayout.setError("*required");
                        username.requestFocus();
                    }
                    else {
                        usernameLayout.setErrorEnabled(false);
                    }

                    if (uPass.isEmpty()){
                        passLayout.setError("*required");
                        pass.requestFocus();
                    }
                    else {
                        passLayout.setErrorEnabled(false);
                    }
                }
                else {
                    dialog.show();

                    if(uName.equals("admin") && uPass.equals("admin")){
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                        finish();
                        dialog.cancel();
                    }
                    else {
                        usernameLayout.setErrorEnabled(false);
                        passLayout.setErrorEnabled(false);
                        Toast.makeText(getApplicationContext(), "Wrong username or password!", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                }

            }
        });
    }

}