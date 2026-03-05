package com.example.gstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gstore.dto.requestDTO.ProductCreateRequest;
import com.example.gstore.dto.requestDTO.ProductUpdateRequest;
import com.example.gstore.model.Category;
import com.example.gstore.model.Product;
import com.example.gstore.repository.CategoryRepository;
import com.example.gstore.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    public Product addProduct(ProductCreateRequest request){
        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImages(request.getImages());
        Category category = categoryRepository.findById(request.getCategoryId())
            .orElseThrow(()-> new RuntimeException("Không tìm thấy danh mục !"));
        product.setCategory(category);
        product.setStock(request.getStock());
        return productRepository.save(product);
    }
    public Product updateProduct(String id,ProductUpdateRequest request){
        Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm !"));
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImages(request.getImages());
        Category category = categoryRepository.findById(request.getCategoryId()).orElseThrow(()->new RuntimeException("Không tìm thấy danh mục sản phẩm !"));
        product.setCategory(category);
        product.setStock(request.getStock());
        return productRepository.save(product);
    }
    public void deletedProduct(String id){
        Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm !"));
        product.setDeleted(true);
        productRepository.save(product);
    }
    @Transactional
    public void updateStock(String id,Integer quantity){
        if(quantity == null || quantity <= 0){throw new RuntimeException("Nhập số lượng > 0  !");}
        Product product = productRepository.findById(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm !"));

        if(product.getStock()<quantity){
            throw new RuntimeException("Không đủ số lượng !");
        }
        int newStock = product.getStock()- quantity;
        product.setStock(newStock);
        productRepository.save(product);
    }
    @Transactional
    public void statusProduct(String id){
        Product product = productRepository.findByIdAndDeletedFalse(id).orElseThrow(()-> new RuntimeException("Không tìm thấy sản phẩm !"));
        product.setStatus(!product.isStatus());
        productRepository.save(product);
    }
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }
}
