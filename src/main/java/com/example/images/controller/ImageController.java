package com.example.images.controller;

import com.example.images.model.Image;
import com.example.images.service.ImageService;
import com.example.images.service.ImageRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping("/fetch")
    public String fetchImages() {
        imageService.fetchAndStoreImageUrls();
        return "Image URLs fetched and stored successfully.";
    }

    @Autowired
    private ImageRepositoryService imageRepositoryService;

    @GetMapping("/list")
    public List<Image> getAllImages() {
        return imageRepositoryService.getAllImages(); // 从新服务获取数据
    }

    // 根据目录ID获取图片
    @GetMapping("/{catalogId}")
    public List<Image> getImagesByCatalogId(@PathVariable Long catalogId) {
        return imageRepositoryService.getImagesByCatalogId(catalogId); // 根据目录ID获取图片
    }
}