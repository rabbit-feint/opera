package com.example.images.controller;

import com.example.images.model.Icon;
import com.example.images.service.IconService;
import com.example.images.service.IconRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/icons")
public class IconController {

    @Autowired
    private IconService iconService;

    @Autowired
    private IconRepositoryService iconRepositoryService;

    @PostMapping("/fetch")
    public String fetchImages() {
        iconService.fetchAndStoreIconUrls();
        return "Image URLs fetched and stored successfully.";
    }
    // 获取所有图标
    @GetMapping("/list")
    public List<Icon> getAllIcons() {
        return iconRepositoryService.getAllIcons(); // 从服务中获取所有图标
    }

    // 根据目录ID获取图标
    @GetMapping("/{catalogId}")
    public List<Icon> getIconsByCatalogId(@PathVariable Long catalogId) {
        return iconRepositoryService.getIconsByCatalogId(catalogId); // 根据目录ID获取图标
    }
}
