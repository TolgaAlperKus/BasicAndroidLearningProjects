package com.tolgaalperkus.artbookfragment;


import android.graphics.Bitmap;

public class ArtItems {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String artName;
    private String year;
    private String painterName;
    private Bitmap image;

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public ArtItems(String artName, String year, String painterName, Bitmap image,int id) {
        this.id=id;
        this.artName = artName;
        this.year = year;
        this.painterName = painterName;
        this.image = image;
    }

    public String getArtName() {
        return artName;
    }

    public String getYear() {
        return year;
    }

    public String getPainterName() {
        return painterName;
    }

    public void setArtName(String artName) {
        this.artName = artName;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setPainterName(String painterName) {
        this.painterName = painterName;
    }
}
