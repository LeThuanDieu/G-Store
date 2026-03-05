package com.example.gstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.gstore.model.Product;

public interface ProductRepository extends MongoRepository<Product,String>{  
    List<Product> findByCategory(String category);
    List<Product> findByDeletedFalse();
    Optional<Product> findByIdAndDeletedFalse(String id);
    // Chỉ lấy những sản phẩm đang được "Bật" hiển thị và chưa bị xóa
    List<Product> findAllByStatusTrueAndDeletedFalse();
}
