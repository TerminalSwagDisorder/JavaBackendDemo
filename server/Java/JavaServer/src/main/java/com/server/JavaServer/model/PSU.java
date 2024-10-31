package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "psu")
public class PSU extends UniversalComponents {
    @Column(name = "Is_ATX12V")
    private String isATX12V;

    @Column(name = "Efficiency")
    private String efficiency;

    @Column(name = "Modular")
    private String modular;

    @Column(name = "Dimensions")
    private String dimensions;
}