package com.server.JavaServer.model;

import lombok.*;
import jakarta.persistence.*;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@MappedSuperclass
public abstract class UniversalComponents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @JsonProperty("ID")
    private Long id;
    
    @Column(name = "Url")
    @JsonProperty("Url")
    private String url;
    
    @Column(name = "Price", precision = 10, scale = 2)
    @JsonProperty("Price")
    private BigDecimal price;

    @Column(name = "Name")
    @JsonProperty("Name")
    private String name;

    @Column(name = "Manufacturer")
    @JsonProperty("Manufacturer")
    private String manufacturer;

    @Column(name = "Image")
    @JsonProperty("Image")
    private String image;

    @Column(name = "Image_Url")
    @JsonProperty("Image_Url")
    private String imageUrl;

}
