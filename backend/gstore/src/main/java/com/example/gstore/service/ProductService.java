package com.example.gstore.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.gstore.dto.requestDTO.admin.ProductCreateRequest;
import com.example.gstore.dto.requestDTO.admin.ProductUpdateRequest;
import com.example.gstore.dto.responseDTO.admin.*;
import com.example.gstore.dto.responseDTO.user.*;
import com.example.gstore.dto.responseDTO.user.ProductDetailResponse;
import com.example.gstore.dto.responseDTO.user.ProductListResponse;
import com.example.gstore.dto.responseDTO.user.ProductSearchResponse;
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
    //-------------------------User----------------------

    public Page<ProductListResponse> getProductUser(String q, int page, int size) {
        // Sắp xếp theo _id giảm dần - mới nhất lên đầu
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        Page<Product> productPage;

        if (q != null && !q.isEmpty()) {
            productPage = productRepository.findByNameContainingIgnoreCaseAndStatusTrueAndDeletedFalse(q, pageable);
        } else {
            productPage = productRepository.findAllByStatusTrueAndDeletedFalse(pageable);
        }
        return productPage.map(product -> new ProductListResponse(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getImages(),
                product.getCategory() != null ? product.getCategory().getName() : "Khác"
        ));
    }
    public ProductDetailResponse getDetailProduct(String id){
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm!"));

        String categoryId = (product.getCategory() != null) ? product.getCategory().getId() : null;
        String categoryName = (product.getCategory() != null) ? product.getCategory().getName() : "Chưa phân loại";

        return new ProductDetailResponse(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImages(),
                categoryId,   
                categoryName, 
                product.getStock()
        );
    }
//     public Page<ProductSearchResponse> getProducts(
//         String name,
//         String category,
//         Double minPrice,
//         Double maxPrice,
//         String sort,
//         int page,
//         int size
// ) {
    public Page<ProductListResponse> getProductByCategory(String categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));
        Page<Product> productPage = productRepository.findByCategoryIdAndStatusTrueAndDeletedFalse(categoryId, pageable);
        return productPage.map(product -> new ProductListResponse(
            product.getId(),
            product.getName(),
            product.getPrice(),
            product.getImages(),
            product.getCategory() != null ? product.getCategory().getName() : "Khác"
    ));
}
//     public Page<Product> getAllProducts(int page, int size) {
//         Pageable pageable = PageRequest.of(page, size, Sort.by("_id").descending());
//         return productRepository.findAll(pageable);
// }

 
}
