package com.example.images.service;

import com.example.images.model.Image;

import java.util.List;

public interface ImageRepositoryService {
    List<Image> getAllImages();
    // 根据目录ID获取图片
    List<Image> getImagesByCatalogId(Long catalogId);
}
