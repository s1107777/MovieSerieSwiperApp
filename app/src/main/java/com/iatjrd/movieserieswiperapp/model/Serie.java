package com.iatjrd.movieserieswiperapp.model;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "serie_table")
public class Serie {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "seriename")
    private String seriename;

    @ColumnInfo(name = "seriegenre")
    private String seriegenre;

    @ColumnInfo(name = "seriedescription")
    private String seriedescription;

    @ColumnInfo(name ="serieseasons")
    private String serieseasons;

    @ColumnInfo(name = "serieurl")
    private String serieurl;

    public Serie(@NonNull String seriename, String seriegenre, String seriedescription, String serieseasons, String serieurl) {
        this.seriename = seriename;
        this.seriegenre = seriegenre;
        this.seriedescription = seriedescription;
        this.serieseasons = serieseasons;
        this.serieurl = serieurl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSeriename() {
        return seriename;
    }

    public void setSeriename(String seriename) {
        this.seriename = seriename;
    }

    public String getSeriegenre() {
        return seriegenre;
    }

    public void setSeriegenre(String seriegenre) {
        this.seriegenre = seriegenre;
    }

    public String getSeriedescription() {
        return seriedescription;
    }

    public void setSeriedescription(String seriedescription) {
        this.seriedescription = seriedescription;
    }

    public String getSerieurl() {
        return serieurl;
    }

    public void setSerieurl(String serieurl) {
        this.serieurl = serieurl;
    }

    public String getSerieseasons() {
        return serieseasons;
    }

    public void setSerieseasons(String serieseasons) {
        this.serieseasons = serieseasons;
    }
}