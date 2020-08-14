package com.example.musicsteam;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.musicsteam.util.AppUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PlaySongActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {
    private static final String BASE_URL = "https://p.scdn.co/mp3-preview/";
    private static final String SHARED_PREFS = "sharedPrefs";
    private String songId = "";
    private String title = "";
    private String artiste = "";
    private String fileLink = "";
    private String coverArt = "";
    private String url = "";
    private SeekBar seekBar;
    private String currentTime = "";

    //create a MediaPlayer object with the object name "player"
    private MediaPlayer player = null;

    //position of the song, default is 0, means start from beginning
    private int musicPosition = 0;

    //create a button object that links to the play button on the screen
    private ImageButton btnPlayPause = null; //empty button

    private SongCollection songCollection;
    Song currentSong;
    TextView txtCurrentTime;
    TextView txtTitle;
    TextView txtArtiste;
    ImageView iCoverArt;
    ImageButton btnNext;
    ImageButton btnPrevious;
    ImageButton btnLoop;
    ImageButton btnShuffle;
    ImageView goBack;
    private Handler mHandler;

    private boolean isLoop = false;
    private boolean isShuffle = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        btnPlayPause = findViewById(R.id.btnPlayPause);
        txtCurrentTime = findViewById(R.id.txtCurrentTime);
        seekBar = findViewById(R.id.seek_bar);
        txtTitle = findViewById(R.id.txtSongTitle);
        txtArtiste = findViewById(R.id.txtArtiste);
        iCoverArt = findViewById(R.id.imgCoverArt);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnLoop = findViewById(R.id.loopButton);
        btnShuffle = findViewById(R.id.shuffleButton);
        goBack = findViewById(R.id.goBack);
        songCollection = new SongCollection();
        mHandler = new Handler();
        retrieveData();
        displaySong(title, artiste, coverArt);


        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        player = new MediaPlayer();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.e("PlaySongActivity", "Player end");
                if (isLoop) {
                    player.start();

                } else if (isShuffle) {
                    playShuffle();
                } else {
                    playNext();
                    playSong(true);
                }


            }
        });

        btnLoop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isLoop == true) { //means now the screen (loop) is turned on, and when you click the loop btn which is true, it turns off
                    btnLoop.setImageResource(R.drawable.loop_off); //i clicked a LOOP:ON button, it turns off
                    isLoop = false;
                    Log.e("btnLoop", "LOOP : OFF");
                    btnShuffle.setEnabled(true);
                } else {
                    btnLoop.setImageResource(R.drawable.loop_on);
                    isLoop = true;
                    Log.e("btnLoop", "LOOP : ON");
                    btnShuffle.setEnabled(false);
                }
            }
        });

        btnShuffle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isShuffle) {
                    btnShuffle.setImageResource(R.drawable.shuffle_off); //i clicked a SHUFFLE:ON button, it turns off
                    isShuffle = false;
                    Log.e("isShuffle", "SHUFFLE : OFF");
                    btnLoop.setEnabled(true);
                } else {
                    btnShuffle.setImageResource(R.drawable.shuffle_on);
                    isShuffle = true;
                    Log.e("isShuffle", "SHUFFLE : ON");
                    btnLoop.setEnabled(false);
                }
            }
        });

        player.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int why) {
                String TAG = "ONERROR";
                Log.e(TAG, "onError");
                Log.e(getPackageName(), String.format("Error(%s%s)", what, why));
                if (MediaPlayer.MEDIA_ERROR_UNKNOWN == what) {
                    Log.d(TAG, "MEDIA_ERROR_UNKNOWN");
                    if (MediaPlayer.MEDIA_ERROR_IO == why) {
                        Log.e(TAG, "MEDIA_ERROR_IO");
                    }
                    if (MediaPlayer.MEDIA_ERROR_MALFORMED == why) {
                        Log.e(TAG, "MEDIA_ERROR_MALFORMED");
                    }
                    if (MediaPlayer.MEDIA_ERROR_UNSUPPORTED == why) {
                        Log.e(TAG, "MEDIA_ERROR_UNSUPPORTED");
                    }
                    if (MediaPlayer.MEDIA_ERROR_TIMED_OUT == why) {
                        Log.e(TAG, "MEDIA_ERROR_TIMED_OUT");
                    }
                } else if (MediaPlayer.MEDIA_ERROR_SERVER_DIED == what) {
                    Log.e(TAG, "MEDIA_ERROR_SERVER_DIED");
                }
                return true;
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mHandler.removeCallbacks(mUpdateTimeTask);
                player.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int progress = AppUtil.progressToTimer(seekBar.getProgress(), player.getDuration());
                player.seekTo(progress);
                updateProgressBar();
                player.start();
            }
        });

        btnPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null) return;
                if (player.isPlaying()) {
                    btnPlayPause.setImageResource(R.drawable.song_pausing);
                    pauseSong();
                } else {
                    // not playing -> playing
                    btnPlayPause.setImageResource(R.drawable.song_playing);
                    playSong(true);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
                playSong(true);
                btnPlayPause.setImageResource(R.drawable.song_playing);
            }
        });

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrevious();
                playSong(true);
                btnPlayPause.setImageResource(R.drawable.song_playing);
            }
        });

        btnPlayPause.performClick();
    }

    private void retrieveData() {
        Bundle songData = this.getIntent().getExtras();
        songId = songData.getString("id");
        title = songData.getString("title");
        artiste = songData.getString("artiste");
        fileLink = songData.getString("fileLink");
        coverArt = songData.getString("coverArt");
        currentSong = songCollection.searchById(songId);
        url = BASE_URL + fileLink;
        Log.e("URL", url);
    }

    private void preparePlayer() {
        try {
            player.reset();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(url);
            seekBar.setProgress(0);
            seekBar.setMax(100);
            player.prepare();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void playSong(boolean shouldPlayNext) {
        if (musicPosition > 0 && player != null) {
            // resume song
            player.seekTo(musicPosition);
//            seekBar.setProgress(player.getCurrentPosition());
            updateProgressBar();
            player.start();
            return;
        }

        // else, it's a brand new song
        preparePlayer();
        if (shouldPlayNext) player.start();
        setTitle("Now Playing: " + title + " - " + artiste);
        updateProgressBar();
    }

    private void pauseSong() {
        if (player != null && player.isPlaying()) {
            player.pause();
            musicPosition = player.getCurrentPosition();
            Log.e("Playsongactivity", "progress bar updated");
            updateProgressBar();
        }
    }

    public void updateProgressBar() {
        mHandler.postDelayed(mUpdateTimeTask, 1000);
    }

    private Runnable mUpdateTimeTask = new Runnable() {
        public void run() {
            if (player == null || !player.isPlaying()) {
                mHandler.removeCallbacks(mUpdateTimeTask);
                return;
            }

            int progress = (int) (((float) player.getCurrentPosition() / player.getDuration()) * 100.0);
            long duration = player.getCurrentPosition();
            currentTime = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(duration),
                    TimeUnit.MILLISECONDS.toSeconds(duration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
            );

            txtCurrentTime.setText(currentTime);
            seekBar.setProgress(progress);
            mHandler.postDelayed(this, 100);
        }
    };

    public void playPrevious() {
        currentSong = songCollection.getPrevSong(songId);
        if (currentSong == null) {
            Toast.makeText(PlaySongActivity.this, "Unable to get previous song", Toast.LENGTH_LONG);
            return;
        }

        setSongDetails(currentSong);
        boolean shouldPlayNext = player.isPlaying();
        playSong(shouldPlayNext);
    }

    public void playNext() {
        currentSong = songCollection.getNextSong(songId);
        if (currentSong == null) {
            Toast.makeText(PlaySongActivity.this, "Unable to get next song", Toast.LENGTH_LONG);
            return;
        }

        setSongDetails(currentSong);
        boolean shouldPlayNext = player.isPlaying();
        playSong(shouldPlayNext);
    }

    public void playShuffle() {
        currentSong = songCollection.getNextShuffledSong(songId);
        if (currentSong == null) {
            Toast.makeText(PlaySongActivity.this, "Unable to get next shuffled song", Toast.LENGTH_LONG);
            return;
        }
        setSongDetails(currentSong);
        if (!player.isPlaying()) {
            playSong(true);
        }
    }

    public void showPopup(View v) {
        PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        Menu menuOpts = popup.getMenu();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String songID = sharedPreferences.getString("likedSongID", null);
        if (songID != null) {
            String[] songIDlist = songID.split(",");
            ArrayList<String> list = new ArrayList<>(Arrays.asList(songIDlist));
            if (list.contains(currentSong.getId())) {
                menuOpts.findItem(R.id.like).setTitle("Unlike").setIcon(R.drawable.unlike);
            }
        }

        popup.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Intent goToProfileActivity = new Intent(this, ProfileActivity.class);
                startActivity(goToProfileActivity);
                return true;
            case R.id.share:
                Intent goToSharePage = new Intent(this, ShareActivity.class);
                startActivity(goToSharePage);
                String baseURL = "https://p.scdn.co/mp3-preview/";
                String shareLink = baseURL + fileLink;
                ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("Share Link", shareLink);
                clipboard.setPrimaryClip(clip);
                return true;
            case R.id.like:
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String songID = sharedPreferences.getString("likedSongID", null);
                if (songID == null) {
                    editor.putString("likedSongID", currentSong.getId());
                    String currentSongTitle = currentSong.getTitle();
                    String message = currentSongTitle + " have been added to the Liked Playlist.";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                    editor.apply();
                } else {
                    String[] songIDlist = songID.split(",");
                    ArrayList<String> list = new ArrayList<>(Arrays.asList(songIDlist));
                    if (!list.contains(currentSong.getId())) {
                        songID += "," + currentSong.getId();
                        String currentSongTitle = currentSong.getTitle();
                        String message = currentSongTitle + " have been added to the Liked Playlist";
                        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                        editor.putString("likedSongID", songID);
                        editor.apply();
                    } else {
                        int index = list.indexOf(currentSong.getId());
                        list.remove(index);
                        String newSongsID = "";
                        for (String i : list) {
                            if (i != null && i.length() > 0) {
                                Log.e("I IS", i.length() + ":" + i);
                                newSongsID += i + ",";
                            }
                        }

                        Log.e("newSongsID", newSongsID);
                        editor.putString("likedSongID", newSongsID);
                        editor.apply();
                        Toast.makeText(this, "Removed from Liked Playlist", Toast.LENGTH_SHORT).show();
                    }
                }
                return true;

                case R.id.viewartiste:
                    Intent nextPage = new Intent(this, artistProfile.class);
                    nextPage.putExtra("id", currentSong.getId());
                    nextPage.putExtra("title", currentSong.getTitle());
                    nextPage.putExtra("artiste", currentSong.getArtiste());
                    nextPage.putExtra("fileLink", currentSong.getFileLink());
                    nextPage.putExtra("coverArt", currentSong.getCoverArt());
                    startActivity(nextPage);
            default:
                return false;
        }
    }


    private void setSongDetails(Song song) {
        musicPosition = 0;
        songId = song.getId();
        title = song.getTitle();
        artiste = song.getArtiste();
        fileLink = song.getFileLink();
        coverArt = song.getCoverArt();
        url = BASE_URL + fileLink;
        displaySong(title, artiste, coverArt);
    }

    private void displaySong(String title, String artiste, String coverArt) {
        txtTitle.setText(title);
        txtArtiste.setText(artiste);
        int imageId = AppUtil.getImageIdFromDrawable(this, coverArt);
        iCoverArt.setImageResource(imageId);
    }

}
