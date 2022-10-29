package com.selnix.spotify.service;

import com.selnix.spotify.beans.ArtistBean;
import com.selnix.spotify.beans.CrudArtistBean;
import com.selnix.spotify.beans.FetchArtistResponseBean;
import com.selnix.spotify.beans.PatchArtistBean;
import com.selnix.spotify.entity.Artist;
import com.selnix.spotify.entity.Image;
import com.selnix.spotify.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;
    private final ImageService imageService;

    public void createArtist(ArtistBean bean) {
        boolean notExisting = artistRepository.findBySpotifyId(bean.getSpotifyId()).isEmpty();
        if (notExisting) {
            Artist artist = mapBeanToArtist(bean);
            artistRepository.save(artist);
        }
    }

    public void createArtists(FetchArtistResponseBean bean) {
        bean.getArtistBeans().forEach(this::createArtist);
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

    public Optional<Artist> getArtistById(int id) {
        return artistRepository.findById(id);
    }

    public Optional<Artist> getArtistBySpotifyId(String id) {
        return artistRepository.findBySpotifyId(id);
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
        Optional<Artist> searchedArtist = getArtistById(artistId);
        if (searchedArtist.isEmpty()) {
            return null;
        }
        return mapArtistToCrudArtistBean(searchedArtist.get());
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
        Optional<Artist> searchedArtist = getArtistById(artistId);
        searchedArtist.ifPresent(artist -> artistRepository.deleteById(artist.getId()));
    }

    public void patchArtist(int id, PatchArtistBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Optional<Artist> searchedArtist = getArtistById(id);
        if (searchedArtist.isEmpty()) {
            return;
        }

        Artist artist = searchedArtist.get();

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
            List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
            artist.setImages(images);
        }

        artistRepository.save(artist);

    }

    public void putArtist(int id, PatchArtistBean bean) { //TODO Muss erst die Bilder speichern um den Artist zu updaten

        Optional<Artist> searchedArtist = getArtistById(id);
        if (searchedArtist.isEmpty()) {
            return;
        }

        Artist artist = searchedArtist.get();

        artist.setName(bean.getName());
        List<Image> images = imageService.createImagesFromPatchImageBeans(bean.getImages());
        artist.setImages(images);
        artist.setGenres(bean.getGenres());
        artist.setFollowers(bean.getFollowers());
        artist.setPopularity(bean.getPopularity());
        artist.setType(bean.getType());

        artistRepository.save(artist);
    }
}