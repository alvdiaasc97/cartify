package com.cartify.cartify.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products = new ArrayList<>();

    @Column(name = "activity_time")
    private LocalDateTime activityTime;

    public Long getId() {
        return id;
    }

    public List<Product> getProducts(){
        return products;

    }

    public void setProducts(List<Product> products){
        this.products = products;
    }

    public Double getTotalPrice() {
        return products.stream().mapToDouble(Product::getAmount).sum();
    }

    public void setActivityTime(LocalDateTime activityTime) {
        this.activityTime = activityTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cart ID: ").append(id).append("\n");
        sb.append("Products:\n");

        for (Product product : products) {
            sb.append(product.toString()).append("\n");
        }
        sb.append("Total:" + getTotalPrice());

        return sb.toString();
    }


}
