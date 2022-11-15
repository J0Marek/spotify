package com.selnix.spotify.service;

import com.selnix.spotify.beans.*;
import com.selnix.spotify.entity.Album;
import com.selnix.spotify.entity.Artist;
import com.selnix.spotify.entity.Image;
import com.selnix.spotify.exceptions.EntityDoesNotExistException;
import com.selnix.spotify.repository.AlbumRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional

public class AlbumService {

    private final AlbumRepository albumRepository;
    private final ImageService imageService;
    private final ArtistService artistService;

    public void createAlbums(FetchArtistAlbumsResponseBean bean) {
        bean.getAlbumBeans().forEach(this::createAlbum);
    }

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
        album.setArtists(artistService.getArtistsBySpotifyIds(bean
                .getArtists()
                .stream()
                .map(ArtistBean::getSpotifyId)
                .toList()));

        return (album);
    }

    public Album getAlbumById(int albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new EntityDoesNotExistException("Album with id: " + albumId + "not found!"));
    }

    public CrudAlbumBean getCrudAlbumBeanById(int albumId) {
        Album searchedAlbum = getAlbumById(albumId);
        return mapAlbumToCrudAlbumBean(searchedAlbum);
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

        return bean;
    }

    public void deleteAlbumById(int albumId) {
        Album searchedAlbum = getAlbumById(albumId);
        albumRepository.deleteById(searchedAlbum.getId());
    }

    public void patchAlbum(int id, PatchAlbumBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Album album = getAlbumById(id);

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

        Album album = getAlbumById(id);

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
