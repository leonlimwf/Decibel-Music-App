package com.example.musicsteam;

public class Song {
    private String id;
    private String title;
    private String artiste;
    private String fileLink;
    private double songLength;
    private String coverArt;
    private String currentTime;

    public Song(String _id, String _title, String _artiste, String _fileLink, double _songLength, String _coverArt) {
        this.id = _id;
        this.title = _title;
        this.artiste = _artiste;
        this.fileLink = _fileLink;
        this.songLength = _songLength;
        this.coverArt = _coverArt;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArtiste() {
        return artiste;
    }

    public String getFileLink() {
        return fileLink;
    }

    public double getSongLength() {
        return songLength;
    }

    public String getCoverArt() {
        return coverArt;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public void setSongLength(double songLength) {
        this.songLength = songLength;
    }

    public void setCoverArt(String coverArt) {
        this.coverArt = coverArt;
    }


}
