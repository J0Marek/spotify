package com.selnix.spotify.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FetchArtistAlbumsResponseBean {
    @JsonProperty("items")
    private List<AlbumBean> albumBeans;
}
