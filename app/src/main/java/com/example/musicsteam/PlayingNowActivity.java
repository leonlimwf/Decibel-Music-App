package com.example.musicsteam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
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

public class PlayingNowActivity extends AppCompatActivity {

    MediaPlayer mediaplayer;
    private SongCollection songCollection = new SongCollection();
    Random r;
    private ArrayList<Song> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageView imagebutton;
    private TextView helloTextView2;
    private TextView playingnowsong;
    private TextView playingnowartiste;

    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playingnow);
        createList();
        buildRecyclerView();
        LoginScreenActivity.Credentials credentials = new LoginScreenActivity.Credentials(); //getting my credentials from my loginscreenactivity

        imagebutton = findViewById(R.id.user_profile);
        sharedPref = getSharedPreferences(getString(R.string.sharedpref_profile), Context.MODE_PRIVATE);
        String readProfilePicture = sharedPref.getString("ProfilePictureSave", null);
        Log.e("Value", readProfilePicture+"");
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

        imagebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfilePage();
            }
        });

        //setting up the hello, <user> text
        helloTextView2 = findViewById(R.id.helloNameView);
        helloTextView2.setText("Hello, " + credentials.accountName);

        playingnowsong = findViewById(R.id.playingnowsong);
        playingnowartiste = findViewById(R.id.playingnowartiste);
    }

    public void onClick(View view) {
        if (view == imagebutton) {
            //start the service here
        } else if (view == imagebutton) {
            //stop the service here
        }
    }

    public void openUserProfilePage () {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void createList() {
        mExampleList = new ArrayList<>();
        Song theWayYouLookTonight = new Song("S1001",
                "The Way You Look Tonight",
                "Michael Buble",
                "a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965",
                4.66,
                "michael_buble_collection");

        Song billieJean = new Song("S1002",
                "Billie Jean",
                "Michael Jackson",
                "f504e6b8e037771318656394f532dede4f9bcaea?cid=2afe87a64b0042dabf51f37318616965",
                4.9,
                "billie_jean");

        Song photograph = new Song("S1003",
                "Photograph",
                "Ed Sheeran",
                "097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965",
                4.32,
                "photograph");

        Song wedonttalkanymore = new Song("S1004",
                "We Don't Talk Anymore",
                "Charlie Puth",
                "9c497e095198c3f26f98c244de8219935a908b81?cid=2afe87a64b0042dabf51f37318616965",
                3.63,
                "wedonttalkanymore");

        Song wheredobrokenheartsgo = new Song("S1005",
                "Where Do Broken Hearts Go",
                "One Direction",
                "b6c4226239663648d07c4595a626d3da310013d4?cid=2afe87a64b0042dabf51f37318616965",
                3.82,
                "wheredobrokenheartsgo");

        Song symphony = new Song("S1006",
                "Symphony",
                "Clean Bandit",
                "25afd2d1ae43f4e90083b921d02ea793b4966518?cid=2afe87a64b0042dabf51f37318616965",
                3.55,
                "symphony");

        Song sayso = new Song("S1007",
                "Say So",
                "Nicki Minaj",
                "ea8d3816651dea9f386bff2427ef6cae1500f18c?cid=2afe87a64b0042dabf51f37318616965",
                3.45,
                "sayso");

        Song imusedtoit = new Song("S1008",
                "I'm Used To It",
                "Powfu",
                "987b7654a44047cb5be262b52afc8b618983da98?cid=2afe87a64b0042dabf51f37318616965",
                2.92,
                "imusedtoit");

        Song thriftshop = new Song("S1009",
                "Thrift Shop",
                "Macklemore",
                "26cdc03872031160b4ec74e8b2bd21ae7359c46d?cid=2afe87a64b0042dabf51f37318616965",
                3.93,
                "thriftshop");

        Song salt = new Song("S10010",
                "Salt",
                "Ava Max",
                "bb37c2f9a52a8417b9634acc2959f58a556f7ef3?cid=2afe87a64b0042dabf51f37318616965",
                3.01,
                "salt");

        Song saturdaynights = new Song("S10011",
                "Saturday Nights",
                "Khalid",
                "3ddee02c7ce75dd666d24bf81997adebbed8098e?cid=2afe87a64b0042dabf51f37318616965",
                3.49,
                "saturdaynights");

        Song boss = new Song("S10012",
                "I'm a Boss",
                "Doja Cat",
                "42847ffa3c83fcfce624d09a8d3d39a15d53e199?cid=2afe87a64b0042dabf51f37318616965",
                2.24,
                "boss");

        Song talkingtothemoon = new Song("S10013",
                "Talking to the moon",
                "Bruno Mars",
                "97de5b6d487f491cddfb427c1612f45f5b3c9388?cid=2afe87a64b0042dabf51f37318616965",
                3.63,
                "talkingtothemoon");

        Song playdate = new Song("S10014",
                "Playdate",
                "Melanie Martinez",
                "72adc548b3c77bd00ef8d0ac56846121b63da6e8?cid=2afe87a64b0042dabf51f37318616965",
                3.00,
                "playdate");

        //adding all the songs in
        this.mExampleList.add(theWayYouLookTonight);
        this.mExampleList.add(billieJean);
        this.mExampleList.add(photograph);
        this.mExampleList.add(wedonttalkanymore);
        this.mExampleList.add(wheredobrokenheartsgo);
        this.mExampleList.add(symphony);
        this.mExampleList.add(sayso);
        this.mExampleList.add(imusedtoit);
        this.mExampleList.add(thriftshop);
        this.mExampleList.add(salt);
        this.mExampleList.add(saturdaynights);
        this.mExampleList.add(boss);
        this.mExampleList.add(talkingtothemoon);
        this.mExampleList.add(playdate);
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
