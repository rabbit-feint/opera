package com.example.text.service;

import com.example.text.model.Text;
import com.example.text.repository.TextRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {

    @Autowired
    private TextRepository textRepository;

    public List<Text> getTextsByCatalogId(Long catalogId) {
        return textRepository.findByCatalogId(catalogId);
    }
    // 搜索文本
    public List<Text> searchTexts(String query) {
        return textRepository.findByContentContaining(query);
    }
}