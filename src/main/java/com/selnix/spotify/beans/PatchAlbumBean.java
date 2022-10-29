package com.selnix.spotify.beans;

import lombok.Data;

import java.util.List;

@Data
public class PatchAlbumBean {
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Integer totalTracks;
    private String type;
    private String albumType;
    private List<PatchImageBean> images; //TODO Macht Probleme
    private List<PatchArtistBean> artists; //TODO Macht Probleme
}
