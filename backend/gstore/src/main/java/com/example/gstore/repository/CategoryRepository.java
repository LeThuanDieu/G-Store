package com.example.gstore.repository;



import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.gstore.model.Category;
import java.util.List;
import java.util.Optional;

@Repository

public interface CategoryRepository extends MongoRepository<Category,String>{
    List<Category> findByDeletedFalse();
    // List<Category> getAll();
    Optional<Category> findByIdAndDeletedFalse(String id);
}
