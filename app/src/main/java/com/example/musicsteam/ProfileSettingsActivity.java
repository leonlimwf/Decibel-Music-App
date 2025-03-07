package com.example.musicsteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

public class ProfileSettingsActivity extends AppCompatActivity {

    private ArrayList<Song> mExampleList;
    private Button button;
    private ImageView accountPicture;
    private TextView changeProfilePic;
    private ImageButton goBack2;
    private TextView saveBtn;

    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profilesettings);

        final LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials();

        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE);
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null); //getting the key of my profilepicturesave

        goBack2 = findViewById(R.id.goBack2);
        goBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        saveBtn = findViewById(R.id.savebtn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goBack();
            }
        });

        SongCollection songCollection = new SongCollection();
        mExampleList = songCollection.getSongs();

        button = findViewById(R.id.editprofile_name);
        button.setText(credentials.accountName);

        accountPicture = findViewById(R.id.editprofile_picture);
        if (readProfilePicture == null) {
            accountPicture.setImageResource(credentials.accountPicture); //setting
        } else {
            try {
                Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(readProfilePicture));
                accountPicture.setImageBitmap(bitmap); //setting new profile pcture
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        //where all the changing profile picture work
        changeProfilePic = findViewById(R.id.changeprofilepicbtn);
        changeProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_OPEN_DOCUMENT);
                startActivityForResult(Intent.createChooser(gallery, "Select Picture"), PICK_IMAGE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { //when user is done
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

    public void goBack() {
        onBackPressed();
    }



}
