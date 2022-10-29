package com.selnix.spotify.beans;

import com.selnix.spotify.entity.Artist;
import com.selnix.spotify.entity.Image;
import lombok.Data;

import java.util.List;

@Data
public class CrudAlbumBean {
    private String spotifyId;
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private int totalTracks;
    private String type;
    private String albumType;
    private List<Image> images;
    private List<Artist> artists;
}
