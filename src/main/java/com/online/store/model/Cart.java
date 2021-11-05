package com.online.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "created")
    private LocalDateTime created;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cart", fetch = FetchType.EAGER)
    private List<CartItem> cartItems;
}
