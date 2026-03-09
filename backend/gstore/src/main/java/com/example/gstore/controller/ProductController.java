package com.example.gstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gstore.dto.requestDTO.admin.ProductCreateRequest;
import com.example.gstore.dto.requestDTO.admin.ProductUpdateRequest;
import com.example.gstore.dto.responseDTO.user.ProductDetailResponse;
import com.example.gstore.dto.responseDTO.user.ProductListResponse;
import com.example.gstore.dto.responseDTO.user.ProductSearchResponse;
import com.example.gstore.model.Product;
import com.example.gstore.repository.ProductRepository;
import com.example.gstore.service.ProductService;

import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping("")
    public Product addProduct(@RequestBody ProductCreateRequest request){
        return  productService.addProduct(request);
    }
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody ProductUpdateRequest request){
        return productService.updateProduct(id, request); 
    }
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable String id){
        productService.deletedProduct(id);
        return "Đã xóa thành công !";
    }
    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable String id, @RequestBody Integer quantity){
        productService.updateStock(id,quantity);
        return ResponseEntity.ok("Cập nhật tồn kho thành công !");
    }
    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStock(@PathVariable String id){
        productService.statusProduct(id);
        return ResponseEntity.ok("Đã ẩn sản phẩm : "+id);
    }
    @GetMapping("/getAll")
    public List<Product> getAll(){
       return productService.getAllProducts();
    }
    //--------------------User--------------
    @GetMapping("/list-products")
    public List<ProductListResponse> getAllProduct(){
        return productService.getProductUser();
    }
    @GetMapping("{id}")
    public ProductDetailResponse detailProduct(@PathVariable String id){
        return productService.getDetailProduct(id);
    }
    //-------------
    // @GetMapping
    // public Page<ProductSearchResponse> getProducts(
    //     @RequestParam(required = false) String id,
    //     @RequestParam(required = false) String name,
    //     @RequestParam(required = false) String category,
    //     @RequestParam(required = false) Double minPrice,
    //     @RequestParam(required = false) Double maxPrice,
    //     @RequestParam(required = false) String sort,
    //     @RequestParam(defaultValue = "0") int page,
    //     @RequestParam(defaultValue = "10") int size)
    // {
    //     return productService.getProducts(name, category, minPrice, maxPrice, sort, page, size);
    // }
}
