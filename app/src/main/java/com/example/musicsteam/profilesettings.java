package com.example.musicsteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class profilesettings extends AppCompatActivity {

    private ImageButton gobackBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesettings);

        gobackBtn = (ImageButton) findViewById(R.id.goBack);
        gobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMainPage();
            }
        });
    }

    public void goToMainPage() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
