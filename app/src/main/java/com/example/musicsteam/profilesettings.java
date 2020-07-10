package com.example.musicsteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profilesettings extends AppCompatActivity {

    private ImageButton gobackBtn;
    private TextView accountName;
    private ImageButton logoutBtn;
    private ImageView accountPicture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesettings);

        gobackBtn = findViewById(R.id.goBack);
        gobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainPage();
            }
        });

        accountName = findViewById(R.id.accountName);
        loginScreen.Credentials credentials = new loginScreen.Credentials();
        accountName.setText(credentials.accountName);


        logoutBtn = findViewById(R.id.logoutBtn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        accountPicture = findViewById(R.id.accountPicture);
        accountPicture.setImageResource(credentials.accountPicture);
    }

    public void goToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logout() {
        Intent intent = new Intent(this, splashScreen.class);
        startActivity(intent);
    }
}
