package com.online.store.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "store_user")
@Getter
@Setter
public class StoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @Column(name = "is_active")
    private Integer isActive;
}
