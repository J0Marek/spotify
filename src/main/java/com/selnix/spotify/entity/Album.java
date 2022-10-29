package com.selnix.spotify.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Indexed
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String spotifyId;
    @FullTextField
    private String name;
    private String releaseDate;
    private String releaseDatePrecision;
    private Integer totalTracks;
    private String type;
    private String albumType;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Artist> artists;

}
