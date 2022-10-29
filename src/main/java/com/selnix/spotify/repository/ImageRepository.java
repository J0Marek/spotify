package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Image;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ImageRepository extends CrudRepository<Image, Integer> {
    Optional<Image> findById(String id);
}
