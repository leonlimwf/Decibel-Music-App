package com.example.musicsteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class profilesettings extends AppCompatActivity {

    private ImageButton gobackBtn;
    private TextView accountName;
    private ImageButton logoutBtn;
    private ImageView accountPicture;
    private Button editBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesettings);

        gobackBtn = findViewById(R.id.goBack);
        gobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onBackPressed();
            }
        });


        loginScreen.Credentials credentials = new loginScreen.Credentials();
        accountName = findViewById(R.id.accountName);
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

        editBtn = findViewById(R.id.btnEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileSettings();
            }
        });
    }

    public void logout() {
        Intent intent = new Intent(this, splashScreen.class);
        startActivity(intent);
    }

    public void goToProfileSettings() {
        Intent intent = new Intent (this, EditProfileActivity.class);
        startActivity(intent);
    }
}
