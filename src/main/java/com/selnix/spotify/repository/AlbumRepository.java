package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Album;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AlbumRepository extends CrudRepository<Album, Integer> {

    @Query("SELECT a FROM Album a WHERE a.spotifyId = :spotifyId")
    Optional<Album> findBySpotifyId(@Param("spotifyId") String spotifyId);

    void deleteById(int id);
}
