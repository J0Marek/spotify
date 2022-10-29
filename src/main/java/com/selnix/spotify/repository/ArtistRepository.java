package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Artist;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    @Query("SELECT a FROM Artist a WHERE a.spotifyId = :spotifyId")
    Optional<Artist> findBySpotifyId(@Param("spotifyId") String spotifyId);

    Optional<Artist> findById(int id);

    void deleteById(int id);
}
