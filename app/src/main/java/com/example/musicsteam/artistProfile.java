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
    ArrayList<Song> filteredList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_profile);

        //basically adding the songs in, without having to manually do the .add as it fetches it instantaneously
        SongCollection songCollection = new SongCollection();
        mExampleList = songCollection.getSongs();

        buildRecyclerView();
        searchSpecificSong(); //search artist's song database
        Bundle songData = this.getIntent().getExtras(); // a bundle is something used to pass data from one activity to another, like a package || getExtra() is getting the value that is stored inside
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        Resources res = getResources(); //getting resources
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
        //my bundle, as said earlier on top
        Bundle songData = this.getIntent().getExtras();
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");

        filteredList.clear();
        for (Song myitem : mExampleList) { //looping thru my mExampleList that stores all my song
                if (myitem.getArtiste().contains(artiste)) { //if myitem.getArtiste contains artiste value
                    filteredList.add(myitem); //item will be added into filtered list
                }
        }
        mAdapter.filterList(filteredList); //adapter will filter based on the filtered list data that was being passed
    }


    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this); //helps in positioning
        mAdapter = new ExampleAdapter(getApplicationContext(),mExampleList ); //adapts a collection of my songs in my example_item
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) { //position of my song
                Song item = filteredList.get(position); //getting the position of the songs in filteredList starting from 0
                Log.e("position", String.valueOf(position));
                Intent nextPage = new Intent(getApplicationContext(), PlaySongActivity.class);

                //my specific song data will be here
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