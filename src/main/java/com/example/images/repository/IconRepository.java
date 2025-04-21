package com.example.images.repository;

import com.example.images.model.Icon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IconRepository extends JpaRepository<Icon, Long> {
    List<Icon> findByCatalogId(Long catalogId); // 根据目录ID获取对应的图标
}
