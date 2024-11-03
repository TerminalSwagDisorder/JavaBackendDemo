package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "gpu")
public class GPU extends UniversalComponents {
    @Column(name = "Cores")
    @JsonProperty("Cores")
    private String cores;

    @Column(name = "Core_Clock")
    @JsonProperty("Core_Clock")
    private String coreClock;

    @Column(name = "Memory")
    @JsonProperty("Memory")
    private String memory;

    @Column(name = "Interface")
    @JsonProperty("Interface")
    private String interfaceType;

    @Column(name = "Dimensions")
    @JsonProperty("Dimensions")
    private String dimensions;

    @Column(name = "TDP")
    @JsonProperty("TDP")
    private String tdp;
}
