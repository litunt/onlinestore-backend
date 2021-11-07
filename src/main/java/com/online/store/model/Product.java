package com.online.store.model;

import com.online.store.utils.PetType;
import com.online.store.utils.ProductCategory;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "pet_type")
    @Enumerated(EnumType.STRING)
    private PetType petType;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory category;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "amount_available")
    private Integer amountAvailable;

    public Product() {

    }
}
