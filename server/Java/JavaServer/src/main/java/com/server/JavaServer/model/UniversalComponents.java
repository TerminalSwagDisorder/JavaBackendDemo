package com.server.JavaServer.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Data
@MappedSuperclass
public abstract class UniversalComponents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "Url")
    private String url;
    
    @Column(name = "Price", precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "Name")
    private String name;

    @Column(name = "Manufacturer")
    private String manufacturer;

    @Column(name = "Image")
    private String image;

    @Column(name = "Image_Url")
    private String imageUrl;

}
