package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "gpu")
public class GPU extends UniversalComponents {
    @Column(name = "Cores")
    private String cores;

    @Column(name = "Core_Clock")
    private String coreClock;

    @Column(name = "Memory")
    private String memory;

    @Column(name = "Interface")
    private String interfaceType;

    @Column(name = "Dimensions")
    private String dimensions;

    @Column(name = "TDP")
    private String tdp;
}
