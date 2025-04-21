package com.example.text.service;

import com.example.text.model.Category;
import com.example.text.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    public List<Category> getTopCategories() {
        return categoryRepository.findByParentId(null);
    }

    public List<Category> getSubCategories(Long parentId) {
        return categoryRepository.findByParentId(parentId);
    }

    // 搜索目录
    public List<Category> searchCategories(String query) {
        return categoryRepository.findByTitleContaining(query);
    }

    // 根据目录ID获取目录
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }
}