package com.selnix.spotify.service;

import com.selnix.spotify.beans.ArtistBean;
import com.selnix.spotify.beans.CrudArtistBean;
import com.selnix.spotify.beans.FetchArtistResponseBean;
import com.selnix.spotify.beans.PatchArtistBean;
import com.selnix.spotify.entity.Artist;
import com.selnix.spotify.entity.Image;
import com.selnix.spotify.exceptions.EntityDoesNotExistException;
import com.selnix.spotify.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service

public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ImageService imageService;


    public void createArtists(FetchArtistResponseBean bean) {
        bean.getArtistBeans().forEach(this::createArtist);

    }

    public void createArtist(ArtistBean bean) {
        boolean notExisting = artistRepository.findBySpotifyId(bean.getSpotifyId()).isEmpty();
        if (notExisting) {
            Artist artist = mapBeanToArtist(bean);
            artistRepository.save(artist);
        }
    }


    public Artist mapBeanToArtist(ArtistBean bean) {
        Artist artist = new Artist();

        artist.setSpotifyId(bean.getSpotifyId());
        artist.setGenres(bean.getGenres());
        artist.setType(bean.getType());
        artist.setPopularity(bean.getPopularity());
        artist.setName(bean.getName());
        artist.setFollowers(bean.getFollowerBean().getTotal());
        artist.setImages(imageService.mapBeansToImages(bean.getImages()));

        return artist;
    }

    public Artist getArtistById(int id) {
        return artistRepository.findById(id)
                .orElseThrow(() -> new EntityDoesNotExistException("Artist with id: " + id + "not found!"));
    }

    public List<Artist> createArtistsFromPatchArtistBeans(List<PatchArtistBean> beans) {
        return beans.stream().map(this::createArtistFromPatchArtistBean).toList();
    }

    public Artist createArtistFromPatchArtistBean(PatchArtistBean bean) {
        Artist artist = mapPatchBeanToArtist(bean);
        return artistRepository.save(artist);
    }

    public Artist mapPatchBeanToArtist(PatchArtistBean bean) {

        Artist artist = new Artist();

        artist.setName(bean.getName());
        List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
        artist.setImages(images);
        artist.setGenres(bean.getGenres());
        artist.setFollowers(bean.getFollowers());
        artist.setPopularity(bean.getPopularity());
        artist.setType(bean.getType());
        return artist;
    }

    public CrudArtistBean getCrudArtistBeanById(int artistId) {
        Artist searchedArtist = getArtistById(artistId);
        return mapArtistToCrudArtistBean(searchedArtist);
    }

    public CrudArtistBean mapArtistToCrudArtistBean(Artist artist) {
        CrudArtistBean bean = new CrudArtistBean();

        bean.setSpotifyId(artist.getSpotifyId());
        bean.setName(artist.getName());
        bean.setImages(artist.getImages());
        bean.setGenres(artist.getGenres());
        bean.setFollowers(artist.getFollowers());
        bean.setPopularity(artist.getPopularity());
        bean.setType(artist.getType());

        return bean;
    }

    public void deleteArtistById(int artistId) {
        Artist artist = getArtistById(artistId);
        artistRepository.deleteById(artist.getId());
    }

    public void patchArtist(int id, PatchArtistBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Artist artist = getArtistById(id);

        if (bean.getName() != null) {
            artist.setName(bean.getName());
        }
        if (bean.getPopularity() != null) {
            artist.setPopularity(bean.getPopularity());
        }
        if (bean.getType() != null) {
            artist.setType(bean.getType());
        }
        if (bean.getFollowers() != null) {
            artist.setFollowers(bean.getFollowers());
        }
        if (bean.getGenres() != null) {
            artist.setGenres(bean.getGenres());
        }
        if (bean.getImages() != null) {
            artist.setImages(imageService.createImagesFromPatchImageBeans(bean.getImages()));
        }
        artistRepository.save(artist);
    }

    public void putArtist(int id, PatchArtistBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Artist artist = getArtistById(id);

        artist.setName(bean.getName());
        List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
        artist.setImages(images);
        artist.setGenres(bean.getGenres());
        artist.setFollowers(bean.getFollowers());
        artist.setPopularity(bean.getPopularity());
        artist.setType(bean.getType());

        artistRepository.save(artist);
    }

    public List<Artist> getArtistsBySpotifyIds(List<String> spotifyIds) {
        return artistRepository.findAllBySpotifyIdIn(spotifyIds);
    }
}