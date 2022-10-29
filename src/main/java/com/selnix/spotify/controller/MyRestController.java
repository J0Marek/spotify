package com.selnix.spotify.controller;

import com.selnix.spotify.beans.CrudAlbumBean;
import com.selnix.spotify.beans.CrudArtistBean;
import com.selnix.spotify.beans.PatchAlbumBean;
import com.selnix.spotify.beans.PatchArtistBean;
import com.selnix.spotify.service.AlbumService;
import com.selnix.spotify.service.ArtistService;
import com.selnix.spotify.service.SpotifyService;
import lombok.RequiredArgsConstructor;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MyRestController {

    private final SpotifyService spotifyService;
    private final ArtistService artistService;
    private final AlbumService albumService;

    /// SPOTIFY API ///
    @GetMapping("/search/Albums/{artistId}")
    public void fetchAlbumsfromArtist(@PathVariable String artistId) throws OAuthProblemException, OAuthSystemException {
        spotifyService.fetchAlbums(artistId);
    }

    @GetMapping("/search/Artist/{artistId}")
    public void fetchArtist(@PathVariable List<String> artistIds) throws OAuthProblemException, OAuthSystemException {
        spotifyService.fetchArtist(artistIds);
    }

    /// CRUD OPERATIONS ///
    // Artist //
    @GetMapping("/get/Artist/{artistId}")
    public ResponseEntity<CrudArtistBean> getArtist(@PathVariable int artistId) {
        CrudArtistBean artistBean = artistService.getCrudArtistBeanById(artistId);
        if (artistBean == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistBean);
    }

    @DeleteMapping("/delete/Artist/{artistId}")
    public void deleteArtist(@PathVariable int artistId) {
        artistService.deleteArtistById(artistId);
    }

    @PatchMapping("/patch/Artist/{artistId}")
    public void patchArtist(@PathVariable int artistId, @RequestBody PatchArtistBean bean) {
        artistService.patchArtist(artistId, bean);
    }

    @PutMapping("/put/Artist/{artistId}")
    public void putArtist(@PathVariable int artistId, @RequestBody PatchArtistBean bean) {
        artistService.putArtist(artistId, bean);
    }

    // Album //
    @GetMapping("/get/Album/{albumId}")
    public ResponseEntity<CrudAlbumBean> getAlbum(@PathVariable int albumId) {
        CrudAlbumBean albumBean = albumService.getCrudAlbumBeanById(albumId);
        if (albumBean == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(albumBean);
    }

    @DeleteMapping("/delete/Album/{albumId}")
    public void deleteAlbum(@PathVariable int albumId) {
        albumService.deleteAlbumById(albumId);
    }

    @PatchMapping("/patch/Album/{albumId}")
    public void patchAlbum(@PathVariable int albumId, @RequestBody PatchAlbumBean bean) {
        albumService.patchAlbum(albumId, bean);
    }

    @PutMapping("/put/Album/{albumId}")
    public void putAlbum(@PathVariable int albumId, @RequestBody PatchAlbumBean bean) {
        albumService.putAlbum(albumId, bean);
    }

    /// ELASTICSEARCH ///


}