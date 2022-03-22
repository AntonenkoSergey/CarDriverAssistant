package com.example.cardriverassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputLayout;

public class Authorization extends AppCompatActivity {
TextView add;
EditText login;
EditText password;
TextInputLayout wrapping_login;
TextInputLayout wrapping_password;

Button button_to_come;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        add = (TextView) findViewById(R.id.add_clik);

        wrapping_login = (TextInputLayout)findViewById(R.id.wrapping_login);
        login = (EditText) wrapping_login.findViewById(R.id.login_text);

        /*wrapping_password = (TextInputLayout) wrapping_password.findViewById(R.id.wrapping_password);
        password= (EditText) wrapping_password.findViewById(R.id.password_text);*/

        button_to_come = (Button) findViewById(R.id.button_to_come);
    }
    public void OnClick_add(View view) {
        login.setText("Все ок)");
    }
    public void OnClick_button_to_come(View view) {
        login.setText("Все ок)");
    }

}