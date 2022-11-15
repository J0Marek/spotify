package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Artist;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ArtistRepository extends CrudRepository<Artist, Integer> {
    Optional<Artist> findBySpotifyId(String spotifyId);

    Optional<Artist> findById(int id);

    void deleteById(int id);

    List<Artist> findAllBySpotifyIdIn(List<String> spotifyIds);
}
