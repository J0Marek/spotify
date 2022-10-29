package com.selnix.spotify.service;

import com.selnix.spotify.beans.FetchArtistAlbumsResponseBean;
import com.selnix.spotify.beans.FetchArtistResponseBean;
import lombok.RequiredArgsConstructor;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.apache.tomcat.util.buf.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SpotifyService {
    private final AccessTokenService accessTokenService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    public void fetchAlbums(String spotifyID) throws OAuthProblemException, OAuthSystemException {

        WebClient webClient = WebClient.create("https://api.spotify.com");
        String url = "/v1/artists/" + spotifyID + "/albums/";

        webClient.get()
                .uri(url)
                .header("Authorization", accessTokenService.getAccessToken())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FetchArtistAlbumsResponseBean.class)
                .subscribe(albumService::createAlbums);
    }

    public void fetchArtist(List<String> spotifyIDs) throws OAuthProblemException, OAuthSystemException {

        String idString = StringUtils.join(spotifyIDs, ',');

        WebClient webClient = WebClient.create("https://api.spotify.com");
        String url = "v1/artists";

        webClient.get()
                .uri(uriBuilder -> uriBuilder.path(url).queryParam("ids", idString).build())
                .header("Authorization", accessTokenService.getAccessToken())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(FetchArtistResponseBean.class)
                .subscribe(artistService::createArtists);
    }
}