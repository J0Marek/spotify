package com.selnix.spotify.controller;

import com.selnix.spotify.service.ElasticSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpResponse;

@RequiredArgsConstructor
@RestController
public class ElasticsearchController {

    private final ElasticSearchService elasticSearchService;

    @GetMapping("/es/artist")
    public ResponseEntity<HttpResponse<String>> getArtist(@RequestParam("artistName") String artistName) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = elasticSearchService.getArtist(artistName);
        System.out.println("The artist response = " + response);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/es/album")
    public ResponseEntity<HttpResponse<String>> getAlbum(@RequestParam("albumName") String albumName) throws URISyntaxException, IOException, InterruptedException {
        HttpResponse<String> response = elasticSearchService.getAlbum(albumName);
        System.out.println("The album response = " + response);
        return ResponseEntity.ok(response);
    }
}
