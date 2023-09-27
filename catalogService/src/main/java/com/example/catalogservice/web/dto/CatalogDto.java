package com.example.catalogservice.web.dto;

import lombok.Data;
import java.io.Serializable;


@Data
public class CatalogDto implements Serializable {

    private String productId;
    private Integer qty;
    private Integer unitPrice;
    private Integer totalPrice;

    private String orderId;
    private String userId;
}
