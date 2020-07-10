package com.example.musicsteam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginScreen extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eEmail;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    private int counter = 3;

    String userEmail = "";
    String userPassword = "";

    /* Class to hold credentials */
    class Credentials
    {
        String email = "leo@mail.com";
        String password = "leo";

        String email1 = "leo1@mail.com";
        String password1 = "leo1";
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        /* Bind the XML views to Java Code Elements */
        eEmail = findViewById(R.id.etEmail);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        eLogin = findViewById(R.id.btnCreateAccount);

        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /* Obtain user inputs */
                userEmail = eEmail.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userEmail.isEmpty() || userPassword.isEmpty()) {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(loginScreen.this, "Please Enter Email & Password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = validate(userEmail, userPassword);

                    /* Validate the user inputs */

                    /* If not valid */
                    if (!isValid) {

                        /* Decrement the counter */
                        counter--;

                        /* Show the attempts remaining */
                        eAttemptsInfo.setText("Attempts Remaining: " + String.valueOf(counter));

                        /* Disable the login button if there are no attempts left */
                        if (counter == 0) {
                            eLogin.setEnabled(false);
                            Toast.makeText(loginScreen.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(loginScreen.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Allow the user in to your app by going into the next activity */
                        startActivity(new Intent(loginScreen.this, MainActivity.class));
                    }

                }
            }
        });
    }

    /* Validate the credentials */
    private boolean validate(String userEmail, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(userEmail.equals(credentials.email) && userPassword.equals(credentials.password))
        {
            return true;
        }

        else if(userEmail.equals(credentials.email1) && userPassword.equals(credentials.password1))
        {
            return true;
        }

        return false;
    }
}
