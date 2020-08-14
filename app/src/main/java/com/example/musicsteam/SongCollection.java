package com.example.musicsteam;

import java.util.ArrayList;
import java.util.Random;

public class SongCollection {
    ArrayList<Song> songs = new ArrayList<>();

    private int currentSongIndex = 0;

    public SongCollection() {
        prepareSongs();
    }

    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public int getCurrentSongIndex() {
        return this.currentSongIndex;
    }

    public Song getNextShuffledSong(String currentSongId) {
        Random rand = new Random();

// Obtain a number between [0 - 49].
        int n = rand.nextInt(5);
        this.currentSongIndex += n;
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

        Song healtheworld = new Song("S10015",
                "Heal the World",
                "Michael Jackson",
                "12261e2c16b7eb17508fb25a411691380893151d?cid=2afe87a64b0042dabf51f37318616965",
                6.41,
                "healtheworld");

        Song supermarketflowers = new Song("S10016",
                "Supermarket Flower",
                "Ed Sheeran",
                "44c5f2f0aba08060bb8f50273931a68e8eea7d34?cid=2afe87a64b0042dabf51f37318616965",
                3.69,
                "supermarketflowers");

        Song onecallaway = new Song("S10017",
                "One Call Away",
                "Charlie Puth",
                "0c4bed645d246486c6b16fe0ac48d418259edecf?cid=2afe87a64b0042dabf51f37318616965",
                3.24,
                "onecallaway");

        Song stealmygirl = new Song("S10018",
                "Steal My Girl",
                "One Direction",
                "2be03a39526b79e665a488afb5f87257a7977dce?cid=2afe87a64b0042dabf51f37318616965",
                3.8,
                "stealmygirl");

        Song castleonthehill = new Song("S10019",
                "Castle on the Hill",
                "Ed Sheeran",
                "beb4ed48cca5d2a792e877c7efe92d54046eac67?cid=2afe87a64b0042dabf51f37318616965",
                4.35,
                "castleonthehill");

        Song baby = new Song("S10020",
                "Baby",
                "Clean Bandit",
                "90fb466f9dcd22ac1ba85ceb70761d6c396f4f5c?cid=2afe87a64b0042dabf51f37318616965",
                3.42,
                "baby");

        Song deathbed = new Song("S10021",
                "Death Bed",
                "Powfu",
                "5bc9716d7c7b66ebe07a641265cd3124f8b9e16c?cid=2afe87a64b0042dabf51f37318616965",
                2.89,
                "deathbed");

        Song soami = new Song("S10022",
                "So Am I",
                "Ava Max",
                "8683916dfa16b85a9dff1bf743e629790a2efe85?cid=2afe87a64b0042dabf51f37318616965",
                3.05,
                "soami");

        Song location = new Song("S10023",
                "Location",
                "Khalid",
                "d50dfb78af7cb29f6363ba1a0d2cbdd707ee137c?cid=2afe87a64b0042dabf51f37318616965",
                3.65,
                "locationmusic");

        Song sweetbutpsycho = new Song("S10024",
                "Sweet But Psycho",
                "Ava Max",
                "9772598a67666a9b7184926c59fe15b9574c7e46?cid=2afe87a64b0042dabf51f37318616965",
                3.12,
                "sweetbutpsycho");

        Song versaceonthefloor = new Song("S10025",
                "Versace On The Floor",
                "Bruno Mars",
                "9487b9c88fabca3470a6ec629d3d61965082bc50?cid=2afe87a64b0042dabf51f37318616965",
                4.35,
                "versaceonthefloor");

        Song crybaby = new Song("S10026",
                "Cry Baby",
                "Melanie Martinez",
                "c305bda2d252631923a0f4281078db6e28e9fc83?cid=2afe87a64b0042dabf51f37318616965",
                4.35,
                "crybaby");



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
        this.songs.add(healtheworld);
        this.songs.add(supermarketflowers);
        this.songs.add(onecallaway);
        this.songs.add(stealmygirl);
        this.songs.add(castleonthehill);
        this.songs.add(baby);
        this.songs.add(deathbed);
        this.songs.add(soami);
        this.songs.add(location);
        this.songs.add(sweetbutpsycho);
        this.songs.add(versaceonthefloor);
        this.songs.add(crybaby);

    }
}
