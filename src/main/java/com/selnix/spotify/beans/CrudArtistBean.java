package com.selnix.spotify.beans;

import com.selnix.spotify.entity.Image;
import lombok.Data;

import java.util.List;

@Data
public class CrudArtistBean {
    private String spotifyId;
    private String name;
    private List<Image> images;
    private List<String> genres;
    private int followers;
    private int popularity;
    private String type;
}
