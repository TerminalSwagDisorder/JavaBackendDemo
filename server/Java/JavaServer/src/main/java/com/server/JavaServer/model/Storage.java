package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "storage")
public class Storage extends UniversalComponents {
    @Column(name = "Capacity")
    @JsonProperty("Capacity")
    private String capacity;

    @Column(name = "Form_Factor")
    @JsonProperty("Form_Factor")
    private String formFactor;

    @Column(name = "Interface")
    @JsonProperty("Interface")
    private String interfaceType;

    @Column(name = "Cache")
    @JsonProperty("Cache")
    private String cache;

    @Column(name = "Flash")
    @JsonProperty("Flash")
    private String flash;

    @Column(name = "TBW")
    @JsonProperty("TBW")
    private String tbw;
}