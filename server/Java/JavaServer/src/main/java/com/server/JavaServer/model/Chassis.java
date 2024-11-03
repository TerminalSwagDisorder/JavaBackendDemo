package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chassis")
public class Chassis extends UniversalComponents {
    @Column(name = "Chassis_type")
    @JsonProperty("Chassis_type")
    private String chassisType;

    @Column(name = "Dimensions")
    @JsonProperty("Dimensions")
    private String dimensions;

    @Column(name = "Color")
    @JsonProperty("Color")
    private String color;

    @Column(name = "Compatibility")
    @JsonProperty("Compatibility")
    private String compatibility;
}