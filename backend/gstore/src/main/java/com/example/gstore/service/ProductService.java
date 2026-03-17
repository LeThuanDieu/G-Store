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

   public List<ProductListResponse> getProductUser(String q) {
    List<Product> products;

    // 1. Kiểm tra nếu có từ khóa tìm kiếm thì dùng hàm tìm kiếm, không thì lấy tất cả
    if (q != null && !q.isEmpty()) {
        products = productRepository.findByNameContainingIgnoreCaseAndStatusTrueAndDeletedFalse(q);
    } else {
        products = productRepository.findAllByStatusTrueAndDeletedFalse();
    }

    // 2. Map dữ liệu sang ProductListResponse
    return products.stream()
            .map(product -> new ProductListResponse(
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getImages(),
                    // Thêm trường này để lấy tên danh mục thay vì DBRef ID
                    product.getCategory() != null ? product.getCategory().getName() : "Khác"
            ))
            .toList();
}
    public ProductDetailResponse getDetailProduct(String id){
    Product product = productRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Không tìm thấy sản phẩm!"));

    // Kiểm tra an toàn để tránh lỗi NullPointerException
    String categoryId = (product.getCategory() != null) ? product.getCategory().getId() : null;
    String categoryName = (product.getCategory() != null) ? product.getCategory().getName() : "Chưa phân loại";

    return new ProductDetailResponse(
            product.getName(),
            product.getDescription(),
            product.getPrice(),
            product.getImages(),
            categoryId,   // Trả về ID để dùng nếu cần (ví dụ: tìm sản phẩm tương tự)
            categoryName, // Trả về Tên để hiển thị lên giao diện
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
    public List<Product> getProductByCategory(String categoryId) {
        // ObjectId cateObjectId = new ObjectId(categoryId);
        return productRepository.findByCategoryIdAndStatusTrueAndDeletedFalse(categoryId);
    }

//      Sort sortOption = Sort.unsorted();
//         if ("price_asc".equals(sort)) sortOption = Sort.by("price").ascending();
//         else if ("price_desc".equals(sort)) sortOption = Sort.by("price").descending();
//         else if ("newest".equals(sort)) sortOption = Sort.by("createdAt").descending();

//         PageRequest pageable = PageRequest.of(page, size, sortOption);

//         // Nếu param null thì đổi thành default
//         name = (name == null) ? "" : name;
//         category = (category == null) ? "" : category;
//         minPrice = (minPrice == null) ? 0.0 : minPrice;
//         maxPrice = (maxPrice == null) ? Double.MAX_VALUE : maxPrice;

//          Page<Product> products = productRepository.searchProducts(name, category, minPrice, maxPrice, pageable);

//         // map sang DTO
//         return products.map(product -> new ProductSearchResponse(
//                 product.getId(),
//                 product.getName(),
//                 product.getPrice(),
//                 product.getImages(),
//                 product.getCategory().getId(),
//                 product.getStock()
//         ));
//     }
}
