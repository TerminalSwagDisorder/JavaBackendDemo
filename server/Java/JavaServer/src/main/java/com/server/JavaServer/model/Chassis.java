package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "chassis")
public class Chassis extends UniversalComponents {
    @Column(name = "Chassis_type")
    private String chassisType;

    @Column(name = "Dimensions")
    private String dimensions;

    @Column(name = "Color")
    private String color;

    @Column(name = "Compatibility")
    private String compatibility;
}