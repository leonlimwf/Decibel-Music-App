package com.example.musicsteam;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicsteam.util.AppUtil;

public class MainActivity extends AppCompatActivity {
    private SongCollection songCollection = new SongCollection();
    private ImageButton button;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (ImageButton) findViewById(R.id.user_profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfilePage();
            }
        });
    }

    public void openUserProfilePage () {
        Intent intent = new Intent(this, profilesettings.class);
        startActivity(intent);
    }

    public void handleSelection(View view) {
        String resourceId = AppUtil.getResourceId(this, view);
        Song selectedSong = songCollection.searchById(resourceId);
        AppUtil.popMessage(this, "Streaming "+ selectedSong.getTitle() + " by " + selectedSong.getArtiste());
        sendDataToActivity(selectedSong);
    }

    public void sendDataToActivity(Song song) {
        Intent nextPage = new Intent(this, PlaySongActivity.class);
        nextPage.putExtra("id", song.getId());
        nextPage.putExtra("title", song.getTitle());
        nextPage.putExtra("artiste", song.getArtiste());
        nextPage.putExtra("fileLink", song.getFileLink());
        nextPage.putExtra("coverArt", song.getCoverArt());
        startActivity(nextPage);
    }
}
