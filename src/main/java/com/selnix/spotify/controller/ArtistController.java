package com.selnix.spotify.controller;

import com.selnix.spotify.beans.CrudArtistBean;
import com.selnix.spotify.beans.PatchArtistBean;
import com.selnix.spotify.service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ArtistController {

    private final ArtistService artistService;

    @GetMapping("/artist/{artistId}")
    public ResponseEntity<CrudArtistBean> getArtist(@PathVariable int artistId) {
        CrudArtistBean artistBean = artistService.getCrudArtistBeanById(artistId);
        if (artistBean == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(artistBean);
    }

    @DeleteMapping("/artist/{artistId}")
    public void deleteArtist(@PathVariable int artistId) {
        artistService.deleteArtistById(artistId);
    }

    @PatchMapping("/artist/{artistId}")
    public void patchArtist(@PathVariable int artistId, @RequestBody PatchArtistBean bean) {
        artistService.patchArtist(artistId, bean);
    }

    @PutMapping("/artist/{artistId}")
    public void putArtist(@PathVariable int artistId, @RequestBody PatchArtistBean bean) {
        artistService.putArtist(artistId, bean);
    }
}
