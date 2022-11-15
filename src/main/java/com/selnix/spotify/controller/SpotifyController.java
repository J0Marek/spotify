package com.selnix.spotify.controller;

import com.selnix.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class SpotifyController {

    private final SpotifyService spotifyService;

    @GetMapping("/spotify/albums/{artistId}")
    public void fetchAlbumsfromArtist(@PathVariable String artistId) throws OAuthProblemException, OAuthSystemException {
        spotifyService.fetchAlbums(artistId);
    }

    @GetMapping("/spotify/artist/{artistId}")
    public void fetchArtist(@PathVariable List<String> artistIds) throws OAuthProblemException, OAuthSystemException {
        spotifyService.fetchArtists(artistIds);
    }
}
