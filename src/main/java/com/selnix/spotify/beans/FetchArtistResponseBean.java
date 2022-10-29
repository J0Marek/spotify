package com.selnix.spotify.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class FetchArtistResponseBean {
    @JsonProperty("artists")
    private List<ArtistBean> artistBeans;
}
