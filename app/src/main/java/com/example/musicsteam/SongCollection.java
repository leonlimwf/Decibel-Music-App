package com.example.musicsteam;

import java.util.ArrayList;

public class SongCollection {
    ArrayList<Song> songs = new ArrayList<>();

    private int currentSongIndex = 0;

    public SongCollection() {
        prepareSongs();
    }

    public Song getNextShuffledSong(String currentSongId) {
        this.currentSongIndex +=3 ;
        if (this.currentSongIndex > this.songs.size() - 1) {
            this.currentSongIndex = 1;
        }

        return this.songs.get(this.currentSongIndex);
    }

    public Song getPrevSong(String currentSongId) {
        this.currentSongIndex -= 1;
        if (this.currentSongIndex < 0) {
            this.currentSongIndex = this.songs.size() - 1;
        }

        return this.songs.get(this.currentSongIndex);
    }

    public Song getNextSong(String currentSongId) {
//        Song song = null;
//        for (int index = 0; index < songs.length; index++) {
//            String tempSongId = songs[index].getId();
//            if (tempSongId.equals(currentSongId) && (index < songs.length - 1)) {
//                song = songs[index + 1];
//                break;
//            }
//        }
//        return song;

        this.currentSongIndex += 1;
        if (this.currentSongIndex > this.songs.size() - 1) {
            this.currentSongIndex = 0;
        }

        return this.songs.get(this.currentSongIndex);
    }



    public Song searchById(String id) {
        Song selectedSong = null;

        for (int index = 0; index < this.songs.size(); index++) {
            if (this.songs.get(index).getId().equals(id)) {
                this.currentSongIndex = index;
                selectedSong = this.songs.get(index);
            }
        }

        return selectedSong;
    }

    private void prepareSongs() {
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
        this.songs.add(theWayYouLookTonight);
        this.songs.add(billieJean);
        this.songs.add(photograph);
        this.songs.add(wedonttalkanymore);
        this.songs.add(wheredobrokenheartsgo);
        this.songs.add(symphony);
        this.songs.add(sayso);
        this.songs.add(imusedtoit);
        this.songs.add(thriftshop);
        this.songs.add(salt);
        this.songs.add(saturdaynights);
        this.songs.add(boss);
        this.songs.add(talkingtothemoon);
        this.songs.add(playdate);
//        Collections.shuffle(songs2);
//        for(Song str: songs2){
//            Log.e("LIST", String.valueOf(str));
//        }

    }
}
