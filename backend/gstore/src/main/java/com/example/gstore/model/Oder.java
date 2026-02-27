package com.example.gstore.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import lombok.Data;

@Document(collection = "oders")
@Data
public class Oder {
    @Id
    private String id;
    private String userId; //id người mua
    private List<OderItem> items; //list sản phẩm trong đơn hàng
    private Double totalPrice;
    private String status;//PENDING, SHIPPING,DELIVERRD,CANCELLED
    private LocalDateTime createAt = LocalDateTime.now();
}
@Data
class OderItem{
    private String productId;
    private String productName;
    private Integer quantity;
    private Double price;
}
