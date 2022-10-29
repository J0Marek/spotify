package com.selnix.spotify.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class AlbumBean {
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    @JsonProperty("release_date")
    private String releaseDate;
    @JsonProperty("release_date_precision")
    private String releaseDatePrecision;
    @JsonProperty("total_tracks")
    private int totalTracks;
    private String type;
    @JsonProperty("album_type")
    private String albumType;
    private List<ImageBean> images;
    private List<ArtistBean> artists;
}