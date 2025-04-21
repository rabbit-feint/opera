package com.example.images.service;

import com.example.images.model.Icon;

import java.util.List;

public interface IconRepositoryService {
    List<Icon> getAllIcons(); // 获取所有图标
    List<Icon> getIconsByCatalogId(Long catalogId); // 根据目录ID获取图标
}

