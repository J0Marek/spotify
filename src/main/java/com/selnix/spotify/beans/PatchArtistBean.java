package com.selnix.spotify.beans;

import lombok.Data;

import java.util.List;

@Data
public class PatchArtistBean {
    private String name;
    private List<PatchImageBean> images; //TODO Macht Probleme
    private List<String> genres;
    private Integer followers;
    private Integer popularity;
    private String type;
}
