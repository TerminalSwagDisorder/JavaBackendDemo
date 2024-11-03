package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cpu_cooler")
public class CpuCooler extends UniversalComponents {
    @Column(name = "Compatibility")
    @JsonProperty("Compatibility")
    private String compatibility;

    @Column(name = "Cooling_Potential")
    @JsonProperty("Cooling_Potential")
    private String coolingPotential;

    @Column(name = "Fan_RPM")
    @JsonProperty("Fan_RPM")
    private String fanRPM;

    @Column(name = "Noise_Level")
    @JsonProperty("Noise_Level")
    private String noiseLevel;

    @Column(name = "Dimensions")
    @JsonProperty("Dimensions")
    private String dimensions;
}
