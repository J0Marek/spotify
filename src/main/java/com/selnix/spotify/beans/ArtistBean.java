package com.selnix.spotify.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;


@Data
public class ArtistBean {
    @JsonProperty("id")
    private String spotifyId;
    private String name;
    private List<ImageBean> images;
    private List<String> genres;
    @JsonProperty("followers")
    private FollowerBean followerBean;
    private int popularity;
    private String type;
}