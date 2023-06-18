package com.alash.shopper.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String description;
    private BigDecimal price;
    private int quantity;
    private BigDecimal averageRating;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
//
//    @ManyToOne
//    @JoinColumn(name = "seller_id")
//    private Seller seller;
//
//    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
//    private List<Review> reviews;

}
