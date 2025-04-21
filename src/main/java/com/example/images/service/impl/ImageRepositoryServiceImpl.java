package com.example.images.service.impl;

import com.example.images.model.Image;
import com.example.images.repository.ImageRepository;
import com.example.images.service.ImageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageRepositoryServiceImpl implements ImageRepositoryService {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        // 获取所有图片
        return imageRepository.findAll();
    }

    // 根据目录ID获取图片
    @Override
    public List<Image> getImagesByCatalogId(Long catalogId) {
        // 使用 ImageRepository 查询指定目录下的所有图片
        return imageRepository.findByCatalogId(catalogId);
    }
}
