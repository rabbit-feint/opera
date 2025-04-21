package com.example.images.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.example.images.model.Icon;
import com.example.images.repository.IconRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;

@Service
public class IconService {

    @Autowired
    private IconRepository iconRepository; // 引入 JPA Repository

    // 配置文件中读取第一个存储桶的配置信息
    @Value("${aliyun.oss.endpoint}")
    private String endpoint;

    @Value("${aliyun.oss.bucket1.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.oss.bucket1.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.oss.bucket1.bucketName}")
    private String bucketName;

    @Transactional
    public void fetchAndStoreIconUrls() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            ListObjectsRequest request = new ListObjectsRequest(bucketName);
            ObjectListing objectListing;
            do {
                objectListing = ossClient.listObjects(request);
                List<OSSObjectSummary> objectSummaries = objectListing.getObjectSummaries();

                for (OSSObjectSummary objectSummary : objectSummaries) {
                    // 获取文件名称
                    String objectName = objectSummary.getKey();
                    // 构建文件的URL
                    String fileUrl = "https://" + bucketName + "." + endpoint + "/" + objectName;

                    // 只需插入 URL
                    Icon icon = new Icon();
                    icon.setUrl(fileUrl); // 设置图标的 URL
                    try {
                        iconRepository.save(icon);
                    } catch (Exception e) {
                        // 记录错误日志
                        System.err.println("Failed to save icon: " + e.getMessage());
                        e.printStackTrace();
                    }
                }

                // 如果还有更多对象，设置分页标记
                request.setMarker(objectListing.getNextMarker());
            } while (objectListing.isTruncated());
        } catch (Exception e) {
            // 记录错误日志
            System.err.println("Error fetching and storing icon URLs: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
    }
}
