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
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HomePage extends AppCompatActivity {


    private SongCollection songCollection = new SongCollection();
    private ArrayList<Song> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView imagebutton;
    private TextView helloTextView2;
    private ImageView searchBar;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //getting my songs from song collection without manually using the .add method
        SongCollection songCollection = new SongCollection();
        mExampleList = songCollection.getSongs();


        buildRecyclerView(); //recyclerview
        LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials(); //getting my credentials from my loginscreenactivity


        imagebutton = findViewById(R.id.user_profile);
        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE); //getting my sharedpref
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null); //getting my sharedpref key of ProfilePictureSave
        if (readProfilePicture == null) {
            imagebutton.setImageResource(credentials.accountPicture); //meaning that i have no "New" profile picture, thus set the default
        } else {
            try {
                Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(readProfilePicture)); //if i got a new one, then i set the new profile picture
                imagebutton.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace(); //error catching
            }
        }

        //open my ProfileActivity.java
        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfilePage();
            }
        });


        //setting up the hello, <user> text
        helloTextView2 = findViewById(R.id.helloNameView);
        helloTextView2.setText("Hello, " + credentials.accountName);


        //my search bar
        searchBar = findViewById(R.id.searchbarview);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToSearchActivity();
            }
        });

    }

    private void goToSearchActivity() {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void openUserProfilePage () {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    //building my recyclerview
    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(getApplicationContext() , mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        //when i click a specific container, it get's the position and bring me to that song
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Song item = mExampleList.get(position);
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

    //function for my chill hits
    public void handleSelectionForChillHits(View view) {
        List<String> chillList = new ArrayList<String>();
        chillList.add("S1001");
        chillList.add("S1003");
        chillList.add("S1005");
        chillList.add("S1007");
        chillList.add("S1009");
        chillList.add("S10011");
        chillList.add("S10013");
        chillList.add("S10015");
        chillList.add("S10017");
        chillList.add("S10019");
        chillList.add("S10021");
        chillList.add("S10023");
        chillList.add("S10025");

        String resourceId = chillList.get(new Random().nextInt(chillList.size()));
        Song selectedSong = songCollection.searchById(resourceId);
        sendDataToActivity(selectedSong);
    }

    //function for my pop hits
    public void handleSelectionForPopHits(View view) {
        List<String> popList = new ArrayList<String>();
        popList.add("S1002");
        popList.add("S1004");
        popList.add("S1006");
        popList.add("S1008");
        popList.add("S10010");
        popList.add("S10012");
        popList.add("S10014");
        popList.add("S10016");
        popList.add("S10018");
        popList.add("S10020");
        popList.add("S10022");
        popList.add("S10024");
        popList.add("S10026");

        String resourceId = popList.get(new Random().nextInt(popList.size()));
        Song selectedSong = songCollection.searchById(resourceId);
        sendDataToActivity(selectedSong);
    }

    //putting all necessary song info into the playsongactivity so that the song will play
    public void sendDataToActivity(Song song) {
        Intent nextPage = new Intent(this, PlaySongActivity.class);
        nextPage.putExtra("id", song.getId());
        nextPage.putExtra("title", song.getTitle());
        nextPage.putExtra("artiste", song.getArtiste());
        nextPage.putExtra("fileLink", song.getFileLink());
        nextPage.putExtra("coverArt", song.getCoverArt());
        startActivity(nextPage);
    }


    //ensure that my profile pics is updated
    //is called anytime an activity is hidden from view, e.g. if you start a new activity that hides it. onResume() is called when the activity that was hidden comes back to view on the screen
    @Override
    protected void onResume() {
        super.onResume();
        LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials();
        imagebutton = findViewById(R.id.user_profile);
        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE);
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null);
        if (readProfilePicture == null) {
            imagebutton.setImageResource(credentials.accountPicture);
        } else {
            try {
                Bitmap bitmap =  MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(readProfilePicture));
                imagebutton.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
