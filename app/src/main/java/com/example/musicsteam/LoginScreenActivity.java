package com.example.musicsteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginScreenActivity extends AppCompatActivity {

    /* Define the UI elements */
    private EditText eAccountName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;



    private int counter = 3;
    public boolean guestMode = true;

    String userAccountName = "";
    String userPassword = "";

    /* Class to hold credentials */
    static class Credentials
    {
        String accountName = "slimy";
        String password = "leo";
        int accountPicture = R.drawable.symphony;
    }

    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        /* Bind the XML views to Java Code Elements */
        eAccountName = findViewById(R.id.eAccountName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttempts);
        eLogin = findViewById(R.id.btnCreateAccount);

        /* Describe the logic when the login button is clicked */
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Obtain user inputs */
                userAccountName = eAccountName.getText().toString();
                userPassword = ePassword.getText().toString();

                /* Check if the user inputs are empty */
                if(userAccountName.isEmpty() || userPassword.isEmpty()) {
                    /* Display a message toast to user to enter the details */
                    Toast.makeText(LoginScreenActivity.this, "Please Enter Your Email & Password!", Toast.LENGTH_LONG).show();

                }else {

                    /* Validate the user inputs */
                    isValid = validate(userAccountName, userPassword);

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
                            Toast.makeText(LoginScreenActivity.this, "You have used all your attempts try again later!", Toast.LENGTH_LONG).show();
                        }
                        /* Display error message */
                        else {
                            Toast.makeText(LoginScreenActivity.this, "Incorrect credentials, please try again!", Toast.LENGTH_LONG).show();
                        }
                    }
                    /* If valid */
                    else {

                        /* Allow the user in to your app by going into the next activity */
                        guestMode = false;
                        startActivity(new Intent(LoginScreenActivity.this, HomePage.class));
                        finish();
                    }

                }
            }
        });
    }



    /* Validate the credentials */
    private boolean validate(String accountName, String userPassword)
    {
        /* Get the object of Credentials class */
        Credentials credentials = new Credentials();

        /* Check the credentials */
        if(accountName.equals(credentials.accountName) && userPassword.equals(credentials.password))
        {
            return true;
        }

        return false;
    }
}
