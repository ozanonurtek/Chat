package com.example.ozan.chat;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {

    EditText mLogin;
    Button mLoginButton;
    SharedPreferences mPrefs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLogin = (EditText) findViewById(R.id.editText);
        mLoginButton = (Button) findViewById(R.id.button);
        mPrefs= getApplication().getSharedPreferences("ChatPrefs", 0);
        if(mPrefs.getString("username",null) != null){
            Intent intent = new Intent(Login.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(mLogin.getText().equals(null) || mLogin.getText().length() == 0){
                    Toast.makeText(getBaseContext(), "Kullanıcı adı boş bırakılamaz", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(mPrefs.getString("username", null) == null){
                        mPrefs.edit().putString("username", mLogin.getText().toString()).commit();
                    }
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });
    }
}
