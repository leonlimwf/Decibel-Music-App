package com.example.musicsteam;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class artistProfile extends AppCompatActivity {

    private ArrayList<Song> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView viewArtistImage;
    private TextView viewArtistName;
    private String songId = "";
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private String coverArt = "";
    private String url = "";
    private ImageButton goBackbtn;
    private SongCollection songCollection;
    Song currentSong;
    ArrayList<Song> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_profile);
        SongCollection songCollection = new SongCollection();
        mExampleList = songCollection.getSongs();
        buildRecyclerView();
        searchSpecificSong();
        Bundle songData = this.getIntent().getExtras();
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");
        Resources res = getResources();
        int artisteCoverImage = res.getIdentifier(coverArt , "drawable", getPackageName());
        viewArtistImage = findViewById(R.id.viewArtistimage);
        viewArtistImage.setImageResource(artisteCoverImage);
        viewArtistName = findViewById(R.id.viewArtistName);
        viewArtistName.setText(artiste);
        goBackbtn = findViewById(R.id.goBack);
        goBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void searchSpecificSong() {
        Bundle songData = this.getIntent().getExtras();
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");
        filteredList.clear();
        for (Song myitem : mExampleList) {
                if (myitem.getArtiste().contains(artiste)) {
                    Log.e("artiste", artiste);
                    filteredList.add(myitem);
                    Log.e("tag", String.valueOf(myitem));
                }
        }
        mAdapter.filterList(filteredList);
    }


    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(getApplicationContext(),mExampleList );
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Song item = filteredList.get(position);
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