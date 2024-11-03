package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "psu")
public class PSU extends UniversalComponents {
    @Column(name = "Is_ATX12V")
    @JsonProperty("Is_ATX12V")
    private String isATX12V;

    @Column(name = "Efficiency")
    @JsonProperty("Efficiency")
    private String efficiency;

    @Column(name = "Modular")
    @JsonProperty("Modular")
    private String modular;

    @Column(name = "Dimensions")
    @JsonProperty("Dimensions")
    private String dimensions;
}