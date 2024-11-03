package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "motherboard")
public class Motherboard extends UniversalComponents {
    @Column(name = "Chipset")
    @JsonProperty("Chipset")
    private String chipset;

    @Column(name = "Cpu_Compatibility")
    @JsonProperty("Cpu_Compatibility")
    private String cpuCompatibility;

    @Column(name = "Form_Factor")
    @JsonProperty("Form_Factor")
    private String formFactor;

    @Column(name = "Memory_Compatibility")
    @JsonProperty("Memory_Compatibility")
    private String memoryCompatibility;
}