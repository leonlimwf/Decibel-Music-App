package com.example.musicsteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {

    private ImageButton gobackBtn;
    private TextView accountName;
    private ImageButton logoutBtn;
    private ImageButton likedSongsBtn;
    private ImageView accountPicture;
    private Button editBtn;
    private TextView numberofsongs;
    private TextView songsLengthDuration;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences sharedPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        gobackBtn = findViewById(R.id.goBack);
        gobackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials();
        accountName = findViewById(R.id.accountName);
        accountName.setText(credentials.accountName);

        logoutBtn = findViewById(R.id.logout);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });

        likedSongsBtn = findViewById(R.id.likedSongsBtn);
        likedSongsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLikedSongActivity();
            }
        });

        //getting all my songs in my songcollection
        SongCollection songCollection = new SongCollection();
        ArrayList<Song> songs = songCollection.getSongs();

        int numberOfSongs = songs.size(); //getting my song's size, 26 in this case
        double totalSongsLength = 0.0; //initializing it first

        for (Song item : songs) { //loop thru my songs
            totalSongsLength += item.getSongLength(); //adding songs length
        }

        numberofsongs = findViewById(R.id.numberOfSongs);
        numberofsongs.setText(numberOfSongs+"");

        songsLengthDuration = findViewById(R.id.totalSongsLength);
        songsLengthDuration.setText(String.format("%.2f", totalSongsLength));

        accountPicture = findViewById(R.id.accountPicture);
        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE);
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null);
        if (readProfilePicture == null) { //if there's no new profile picture, it will be set to default
            accountPicture.setImageResource(credentials.accountPicture);
        } else {
            try { //setting the profile image
                Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(readProfilePicture));
                accountPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace(); //error catching
            }
        }

        editBtn = findViewById(R.id.btnEdit);
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToProfileSettings();
            }
        });
    }

    public void logout() {
        Intent intent = new Intent(this, SplashScreenActivity.class);
        startActivity(intent);
    }

    public void goToProfileSettings() {
        Intent intent = new Intent (this, ProfileSettingsActivity.class);
        startActivity(intent);
    }

    public void goToLikedSongActivity() {
        Intent intent = new Intent (this, LikedSongActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //when user is done updating it
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                accountPicture.setImageBitmap(bitmap);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("ProfilePictureSave", imageUri.toString());
                editor.apply();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Override
    protected void onResume() { //Called when the Activity is leaving the foreground and back to the foreground, can be used to release resources or initialize states.
        super.onResume();
        LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials();
        accountPicture = findViewById(R.id.accountPicture);
        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE);
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null);
        Log.e("Value", readProfilePicture + "");
        if (readProfilePicture == null) {
            accountPicture.setImageResource(credentials.accountPicture);
        } else {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(readProfilePicture));
                accountPicture.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
