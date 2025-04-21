package com.example.text.controller;

import com.example.text.model.Category;
import com.example.text.model.Text;
import com.example.text.service.CategoryService;
import com.example.text.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TextService textService;

    // 获取一级目录
    @GetMapping("/categories")
    public List<Category> getCategories() {
        return categoryService.getTopCategories(); // 获取顶级目录
    }

    // 根据父级目录ID获取子目录
    @GetMapping("/categories/{parentId}")
    public List<Category> getSubCategories(@PathVariable Long parentId) {
        return categoryService.getSubCategories(parentId);
    }

    // 根据目录ID获取文本内容
    @GetMapping("/texts/{catalogId}")
    public List<Text> getTextsByCatalogId(@PathVariable Long catalogId) {
        return textService.getTextsByCatalogId(catalogId);
    }

    // 搜索目录和文本
    @GetMapping("/search")
    public List<Category> search(@RequestParam String query) {
        // 搜索匹配的目录
        List<Category> categories = categoryService.searchCategories(query);

        // 搜索匹配的文本，并返回其对应的目录
        List<Text> texts = textService.searchTexts(query);

        // 使用 Set 来去重
        Set<Category> matchedCategories = new HashSet<>(categories);

        // 获取匹配文本的目录
        for (Text text : texts) {
            Category category = categoryService.getCategoryById(text.getCatalogId());
            if (category != null) {
                matchedCategories.add(category);
            }
        }

        // 将去重后的目录转换为列表
        List<Category> result = new ArrayList<>(matchedCategories);

        // 为每个目录添加子目录
        for (Category category : result) {
            List<Category> subCategories = categoryService.getSubCategories(category.getId());
            category.setSubCategories(subCategories);
        }

        return result;
    }
}