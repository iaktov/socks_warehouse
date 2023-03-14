package com.example.socks_warehouse.entity;

import lombok.Data;

import javax.persistence.*;

@Entity(name = "socks_warehouse")
@Data
public class Socks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;
    private Integer cottonPart;
    private Integer quantity;

}
