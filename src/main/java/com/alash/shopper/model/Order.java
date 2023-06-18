package com.alash.shopper.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customer_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private LocalDateTime orderDate;
    private BigDecimal totalAmount;
    private String status;

//    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
//    private List<OrderItem> orderItems;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "shipping_address_id")
//    private Address shippingAddress;

//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "payment_method_id")
//    private Payment paymentMethod;

}
