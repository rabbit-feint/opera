package com.example.text.repository;

import com.example.text.model.Text;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TextRepository extends JpaRepository<Text, Long> {
    List<Text> findByCatalogId(Long catalogId);
    // 根据内容模糊查询文本
    List<Text> findByContentContaining(String query);
}