package com.selnix.spotify.service;

import com.selnix.spotify.beans.ImageBean;
import com.selnix.spotify.beans.PatchImageBean;
import com.selnix.spotify.entity.Image;
import com.selnix.spotify.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ImageService {
    private final ImageRepository imageRepository;

    public Image createImageFromPatchImageBean(PatchImageBean bean) {
        Image image = mapPatchBeanToImage(bean);
        return imageRepository.save(image);
    }

    public List<Image> createImagesFromPatchImageBeans(List<PatchImageBean> beans) {
        return beans.stream().map(this::createImageFromPatchImageBean).toList();
    }

    public Image mapBeanToImage(ImageBean bean) {
        Image image = new Image();
        image.setUrl(bean.getUrl());
        image.setWidth(bean.getWidth());
        image.setHeight(bean.getHeight());
        return image;
    }

    public List<Image> mapBeansToImages(List<ImageBean> beans) {
        List<Image> result = new ArrayList<>();
        if (beans != null) {
            result = beans.stream()
                    .map(this::mapBeanToImage)
                    .toList();
        }
        return result;
    }

    public Image mapPatchBeanToImage(PatchImageBean bean) {
        Image image = new Image();
        image.setUrl(bean.getUrl());
        image.setWidth(bean.getWidth());
        image.setHeight(bean.getHeight());
        return image;
    }
}
