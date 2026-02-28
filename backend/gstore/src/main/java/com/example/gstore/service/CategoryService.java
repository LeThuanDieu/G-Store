package com.example.gstore.service;



import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gstore.dto.requestDTO.CategoryRequest;
import com.example.gstore.dto.responseDTO.CategoryResponse;
import com.example.gstore.dto.responseDTO.CategoryResponseAdmin;
import com.example.gstore.model.Category;
import com.example.gstore.repository.CategoryRepository;

@Service

public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    public Category addCategory(CategoryRequest request){
        Category category = new Category();
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }
    public Category updateCategory(String id ,CategoryRequest request){
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new RuntimeException("Không tìm thấy danh mục !"));
        category.setName(request.getName());
        category.setDescription(request.getDescription());
        return categoryRepository.save(category);
    }
    public void deleteCategory(String id){
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(()->new RuntimeException("Không tìm thấy danh mục !"));
        category.setDeleted(true);
        categoryRepository.save(category);
    }
    public List<CategoryResponse> getAllForUser(){
        List<Category> categories = categoryRepository.findByDeletedFalse();
        return categories.stream()
                     .map(category -> new CategoryResponse(
                        //  category.getId(),
                         category.getName(),
                         category.getDescription()
                     ))
                     .collect(Collectors.toList());
    }
    //  public List<CategoryResponseAdmin> getAll(){
    //     List<Category> categories = categoryRepository.findAll();
    //     return categories.stream()
    //                  .map(category -> new CategoryResponseAdmin(
    //                      category.getId(),
    //                      category.getName(),
    //                      category.getDescription(),
    //                      category.isDeleted()
    //                  ))
    //                  .collect(Collectors.toList());
    // }
    public List<Category> getAll(){
       return categoryRepository.findAll();
    }
    public CategoryResponse findCategoryById(String id){
        Category category = categoryRepository.findByIdAndDeletedFalse(id).orElseThrow(()-> new RuntimeException("Không tìm thấy !"));
        return new CategoryResponse(category.getName(), category.getDescription());
        }
}
