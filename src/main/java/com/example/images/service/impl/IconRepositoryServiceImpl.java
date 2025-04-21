package com.example.images.service.impl;

import com.example.images.model.Icon;
import com.example.images.repository.IconRepository;
import com.example.images.service.IconRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IconRepositoryServiceImpl implements IconRepositoryService {

    @Autowired
    private IconRepository iconRepository;

    @Override
    public List<Icon> getAllIcons() {
        return iconRepository.findAll();
    }

    @Override
    public List<Icon> getIconsByCatalogId(Long catalogId) {
        return iconRepository.findByCatalogId(catalogId); // 根据目录ID获取图标
    }
}

