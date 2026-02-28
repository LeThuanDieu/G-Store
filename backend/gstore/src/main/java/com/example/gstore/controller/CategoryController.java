package com.example.gstore.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.CategoryRequest;
import com.example.gstore.dto.responseDTO.CategoryResponse;
import com.example.gstore.dto.responseDTO.CategoryResponseAdmin;
import com.example.gstore.model.Category;
import com.example.gstore.service.CategoryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/user")
    public List<CategoryResponse> getAllCategory(){
        
        return categoryService.getAllForUser(); 
    }
    @GetMapping("/admin")
    public List<Category> getAll(){
        
        return categoryService.getAll(); 
    }
    @GetMapping("/{id}")
    public CategoryResponse categoryById(@PathVariable String id){
        return categoryService.findCategoryById(id);
    }
    // public String getMethodName(@RequestParam String param) {
    //     return new String();
    // }
    
    @PostMapping("")
    public Category addCategory(@RequestBody CategoryRequest createCategoryRequest){
        return categoryService.addCategory(createCategoryRequest);
    }
    @PutMapping("/{id}")
    public Category updateCategory (@PathVariable String id, @RequestBody CategoryRequest categoryRequest) {
        
        return categoryService.updateCategory(id, categoryRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable String id){
        categoryService.deleteCategory(id);
    }
}   
