package com.selnix.spotify.repository;

import com.selnix.spotify.entity.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepository extends CrudRepository<Image, Integer> {
}
