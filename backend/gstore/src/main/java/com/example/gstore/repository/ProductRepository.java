package com.example.gstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.gstore.model.Product;

public interface ProductRepository extends MongoRepository<Product,String>{  
    List<Product> findByCategory(String category);
    List<Product> findByDeletedFalse();
    Optional<Product> findByIdAndDeletedFalse(String id);
    //Chỉ lấy những sản phẩm đang được "Bật" hiển thị và chưa bị xóa
    List<Product> findAllByStatusTrueAndDeletedFalse();
    //  @Query("{ 'deleted': false, " +
    //        "'name': { $regex: ?0, $options: 'i' }, " +
    //        "'category': { $regex: ?1, $options: 'i' }, " +
    //        "'price': { $gte: ?2, $lte: ?3 } }")
    // Page<Product> searchProducts(String name, String category, Double minPrice, Double maxPrice, Pageable pageable);
}
