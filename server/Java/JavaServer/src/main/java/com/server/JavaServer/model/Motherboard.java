package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "motherboard")
public class Motherboard extends UniversalComponents {
    @Column(name = "Chipset")
    private String chipset;

    @Column(name = "Cpu_Compatibility")
    private String cpuCompatibility;

    @Column(name = "Form_Factor")
    private String formFactor;

    @Column(name = "Memory_Compatibility")
    private String memoryCompatibility;
}