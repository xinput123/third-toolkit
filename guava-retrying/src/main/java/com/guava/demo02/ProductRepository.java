package com.guava.demo02;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author: xinput
 * @Date: 2020-02-18 13:53
 */
public class ProductRepository {

    private static ConcurrentHashMap<Long, Product> products = new ConcurrentHashMap();

    private static AtomicLong ids = new AtomicLong(0);

    public List<Product> findAll() {
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id) {
        return products.get(id);
    }

    public Product updatePrice(Long id, BigDecimal price) {
        Product p = products.get(id);
        if (null == p) {
            return p;
        }
        p.setPrice(price);
        return p;
    }

    public Product addProduct(Product product) {
        Long id = ids.addAndGet(1);
        product.setId(id);
        products.put(id, product);
        return product;
    }
}
