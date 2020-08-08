package com.example.musicsteam;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class LikedSongActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static final String SHARED_PREFS = "sharedPrefs";
    private ImageButton goBackbtn;
    private SongCollection songCollection = new SongCollection();
    ArrayList<Song> newLikedSongData = new ArrayList<>();

    Song likedSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liked_song);
        loadData();
        buildRecyclerView();
        goBackbtn = findViewById(R.id.goBack);
        goBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String readSongData = sharedPreferences.getString("likedSongID", null);
        Log.e("readsongdata", readSongData);
        if (readSongData != null) {
            String[] readSongDataList = readSongData.split(",");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(readSongDataList));
            Log.e("list", String.valueOf(list));
            for (String newSongID : readSongDataList) {
                if (newSongID.length() > 0) {
                    likedSong = songCollection.searchById(newSongID);
                    newLikedSongData.add(likedSong);
                    Log.e("newlikedsongdata", String.valueOf(newLikedSongData));
                }
            }
        } else {
            Toast.makeText(this, "Error Occurred. Please restart the app.", Toast.LENGTH_SHORT).show();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(getApplicationContext(), newLikedSongData);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Song item = newLikedSongData.get(position);
                Log.e("position", String.valueOf(position));
                Intent nextPage = new Intent(getApplicationContext(), PlaySongActivity.class);
                nextPage.putExtra("id", item.getId());
                nextPage.putExtra("title", item.getTitle());
                nextPage.putExtra("artiste", item.getArtiste());
                nextPage.putExtra("fileLink", item.getFileLink());
                nextPage.putExtra("coverArt", item.getCoverArt());
                startActivity(nextPage);
            }

        });
    }


}