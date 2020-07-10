package com.example.musicsteam;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class scrollexample extends AppCompatActivity {

    private ArrayList<ExampleItem> mExampleList;
    private RecyclerView mRecyclerView;
    private ExampleAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImageButton imagebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scroll_example);
        createExampleList();
        buildRecyclerView();
        imagebutton = (ImageButton) findViewById(R.id.userprofiletest);
        imagebutton.setOnClickListener(new View.OnClickListener() {
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

    public void createExampleList() {
        mExampleList = new ArrayList<>();
        mExampleList.add(new ExampleItem(R.drawable.michael_buble_collection, "The Way You Look Tonight", "Michael Buble"));
        mExampleList.add(new ExampleItem(R.drawable.billie_jean, "Billie Jean ", "Michael Jackson"));
        mExampleList.add(new ExampleItem(R.drawable.photograph, "Photograph", "Ed Sheeran"));
        mExampleList.add(new ExampleItem(R.drawable.wedonttalkanymore, "We Don't Talk Anymore", "Charlie Puth"));
        mExampleList.add(new ExampleItem(R.drawable.wheredobrokenheartsgo, "Where Do Broken Hearts Go", "One Direction"));
        mExampleList.add(new ExampleItem(R.drawable.symphony, "Symphony", "Clean Bandit"));
        mExampleList.add(new ExampleItem(R.drawable.sayso, "Say So", "Nicki Minaj"));
        mExampleList.add(new ExampleItem(R.drawable.imusedtoit, "I'm used to it", "Powfu"));
        mExampleList.add(new ExampleItem(R.drawable.thriftshop, "Thrift Shop", "Macklemore"));
        mExampleList.add(new ExampleItem(R.drawable.salt, "Salt", "Ava Max"));
        mExampleList.add(new ExampleItem(R.drawable.saturdaynights, "Saturday Nights", "Khalid"));
        mExampleList.add(new ExampleItem(R.drawable.boss, "Boss", "Doja Cat"));
        mExampleList.add(new ExampleItem(R.drawable.talkingtothemoon, "Talking To The Moon", "Bruno Mars"));






    }

    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(mExampleList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new ExampleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                startActivity(new Intent(scrollexample.this, MainActivity.class));
            }

            @Override
            public void onDeleteClick(int position) {

            }
        });
    }



}
