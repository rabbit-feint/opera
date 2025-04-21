package com.example.images.repository;


import com.example.images.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByCatalogId(Long catalogId);
}
