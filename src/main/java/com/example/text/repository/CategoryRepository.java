package com.example.text.repository;

import com.example.text.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // 根据父级目录ID查询子目录
    List<Category> findByParentId(Long parentId);

    // 根据目录标题模糊查询
    List<Category> findByTitleContaining(String title);
}

