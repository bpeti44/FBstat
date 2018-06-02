package hu.peti.fbstat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;



public class Login extends AppCompatActivity {

    private static String SERVER_URL = "http://example.com";

    private Button mLoginButton;
    public static EditText usernameText;
    public static EditText passwordText;

    public static String username;
    public  static String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameText = findViewById(R.id.usernameText);
        passwordText = findViewById(R.id.passwordText);

        mLoginButton = findViewById(R.id.loginButton);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HTTPCalls.authUser();
                Intent intent = new Intent(Login.this, MainActivity.class);
                finish();
                startActivity(intent);
            }
        });



    }





}
