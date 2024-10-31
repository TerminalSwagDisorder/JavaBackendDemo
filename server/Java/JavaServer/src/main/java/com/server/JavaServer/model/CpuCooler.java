package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cpu_cooler")
public class CpuCooler extends UniversalComponents {
    @Column(name = "Compatibility")
    private String compatibility;

    @Column(name = "Cooling_Potential")
    private String coolingPotential;

    @Column(name = "Fan_RPM")
    private String fanRPM;

    @Column(name = "Noise_Level")
    private String noiseLevel;

    @Column(name = "Dimensions")
    private String dimensions;
}
