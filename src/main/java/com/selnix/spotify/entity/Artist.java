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
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String spotifyId;
    @FullTextField
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Image> images;
    @ElementCollection
    private List<String> genres;
    private Integer followers;
    private Integer popularity;
    private String type;
}