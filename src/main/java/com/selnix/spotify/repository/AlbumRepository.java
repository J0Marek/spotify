package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Album;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Integer> {

    Optional<Album> findBySpotifyId(String spotifyId);

    void deleteById(int id);
}
