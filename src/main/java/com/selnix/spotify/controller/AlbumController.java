package com.selnix.spotify.controller;

import com.selnix.spotify.beans.CrudAlbumBean;
import com.selnix.spotify.beans.PatchAlbumBean;
import com.selnix.spotify.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class AlbumController {

    private final AlbumService albumService;

    @GetMapping("/album/{albumId}")
    public ResponseEntity<CrudAlbumBean> getAlbum(@PathVariable int albumId) {
        return ResponseEntity.ok(albumService.getCrudAlbumBeanById(albumId));
    }

    @DeleteMapping("/album/{albumId}")
    public void deleteAlbum(@PathVariable int albumId) {
        albumService.deleteAlbumById(albumId);
    }

    @PatchMapping("/album/{albumId}")
    public void patchAlbum(@PathVariable int albumId, @RequestBody PatchAlbumBean bean) {
        albumService.patchAlbum(albumId, bean);
    }

    @PutMapping("/album/{albumId}")
    public void putAlbum(@PathVariable int albumId, @RequestBody PatchAlbumBean bean) {
        albumService.putAlbum(albumId, bean);
    }
}
