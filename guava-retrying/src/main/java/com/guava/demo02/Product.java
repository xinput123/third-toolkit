package com.guava.demo02;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Product {

    private Long id;

    private String name;

    private Integer count;

    private BigDecimal price;

}
