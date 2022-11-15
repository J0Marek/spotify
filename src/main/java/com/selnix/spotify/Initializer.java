package com.selnix.spotify;

import com.selnix.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Component
public class Initializer {
    private final SpotifyService spotifyService;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) throws OAuthProblemException, OAuthSystemException {

        List<String> artistIds = new ArrayList<>();
        artistIds.add("0qT79UgT5tY4yudH9VfsdT");
        artistIds.add("6XyY86QOPPrYVGvF9ch6wz");
        artistIds.add("5aYyPjAsLj7UzANzdupwnS");
        artistIds.add("6FBDaR13swtiWwGhX1WQsP");
        artistIds.add("7dkEByOe0oHqc54qU4hwzV");
        artistIds.add("0rEuaTPLMhlViNCJrg3NEH");
        artistIds.add("2Ct54gNxKnYLnXk9HhviMI");
        artistIds.add("7jy3rLJdDQY21OgRLCZ9sD");
        artistIds.add("263rHosE7Ihtqs2Da780ZZ");
        artistIds.add("2PWXHVDEtObSmUrNhfPRav");

        spotifyService.fetchArtists(artistIds);

        artistIds.forEach(spotifyId -> {
            try {
                spotifyService.fetchAlbums(spotifyId);
            } catch (OAuthProblemException | OAuthSystemException e) {
                throw new RuntimeException(e);
            }
        });
    }
}