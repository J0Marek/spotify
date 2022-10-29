package com.selnix.spotify.service;

import com.selnix.spotify.beans.AlbumBean;
import com.selnix.spotify.beans.CrudAlbumBean;
import com.selnix.spotify.beans.FetchArtistAlbumsResponseBean;
import com.selnix.spotify.beans.PatchAlbumBean;
import com.selnix.spotify.entity.Album;
import com.selnix.spotify.entity.Artist;
import com.selnix.spotify.entity.Image;
import com.selnix.spotify.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ImageService imageService;
    private final ArtistService artistService;

    public void createAlbum(AlbumBean bean) {
        boolean notExisting = albumRepository.findBySpotifyId(bean.getSpotifyId()).isEmpty();
        if (notExisting) {
            Album album = mapBeanToAlbum(bean);
            albumRepository.save(album);
        }
    }

    public Album mapBeanToAlbum(AlbumBean bean) {
        Album album = new Album();
        album.setSpotifyId(bean.getSpotifyId());
        album.setName(bean.getName());
        album.setReleaseDate(bean.getReleaseDate());
        album.setReleaseDatePrecision(bean.getReleaseDatePrecision());
        album.setTotalTracks(bean.getTotalTracks());
        album.setType(bean.getType());
        album.setAlbumType(bean.getAlbumType());
        album.setImages(imageService.mapBeansToImages(bean.getImages()));
        return (album);
    }


    public void createAlbums(FetchArtistAlbumsResponseBean bean) {
        bean.getAlbumBeans().forEach(this::createAlbum);
    }

    public Optional<Album> getAlbumById(int albumId) {
        return albumRepository.findById(albumId);
    }

    public CrudAlbumBean getCrudAlbumBeanById(int albumId) {
        Optional<Album> searchedAlbum = getAlbumById(albumId);
        if (searchedAlbum.isEmpty()) {
            return null;
        }
        return mapAlbumToCrudAlbumBean(searchedAlbum.get());
    }

    public CrudAlbumBean mapAlbumToCrudAlbumBean(Album album) {
        CrudAlbumBean bean = new CrudAlbumBean();

        bean.setSpotifyId(album.getSpotifyId());
        bean.setName(album.getName());
        bean.setReleaseDate(album.getReleaseDate());
        bean.setReleaseDatePrecision(album.getReleaseDatePrecision());
        bean.setTotalTracks(album.getTotalTracks());
        bean.setType(album.getType());
        bean.setAlbumType(album.getAlbumType());
        bean.setImages(album.getImages());
        //bean.setArtists(album.getArtists());
        //bean.setAvailableMarkets(album.getAvailableMarkets());

        return bean;
    }

    public void deleteAlbumById(int albumId) {
        Optional<Album> searchedAlbum = getAlbumById(albumId);
        searchedAlbum.ifPresent(album -> albumRepository.deleteById(album.getId()));
    }

    public void patchAlbum(int id, PatchAlbumBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Optional<Album> searchedAlbum = getAlbumById(id);
        if (searchedAlbum.isEmpty()) {
            return;
        }

        Album album = searchedAlbum.get();

        if (bean.getName() != null) {
            album.setName(bean.getName());
        }
        if (bean.getReleaseDate() != null) {
            album.setReleaseDate(bean.getReleaseDate());
        }
        if (bean.getReleaseDatePrecision() != null) {
            album.setReleaseDatePrecision(bean.getReleaseDatePrecision());
        }
        if (bean.getTotalTracks() != null) {
            album.setTotalTracks(bean.getTotalTracks());
        }
        if (bean.getType() != null) {
            album.setType(bean.getType());
        }
        if (bean.getAlbumType() != null) {
            album.setAlbumType(bean.getAlbumType());
        }
        if (bean.getImages() != null) {
            List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
            album.setImages(images);
        }
        if (bean.getArtists() != null) {
            List<Artist> artists = artistService.createArtistsFromPatchArtistBeans(bean.getArtists());
            album.setArtists(artists);
        }
        albumRepository.save(album);
    }

    public void putAlbum(int id, PatchAlbumBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Optional<Album> searchedAlbum = getAlbumById(id);
        if (searchedAlbum.isEmpty()) {
            return;
        }

        Album album = searchedAlbum.get();

        album.setName(bean.getName());
        album.setReleaseDate(bean.getReleaseDate());
        album.setReleaseDatePrecision(bean.getReleaseDatePrecision());
        album.setTotalTracks(bean.getTotalTracks());
        album.setType(bean.getType());
        album.setAlbumType(bean.getAlbumType());
        List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
        album.setImages(images);
        List<Artist> artists = artistService.createArtistsFromPatchArtistBeans(bean.getArtists());
        album.setArtists(artists);
        albumRepository.save(album);
    }
}
