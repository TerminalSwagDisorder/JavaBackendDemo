package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cpu")
public class CPU extends UniversalComponents {
	@Column(name = "Core_Count")
    private String coreCount;
	
	@Column(name = "Thread_Count")
    private String threadCount;
	
	@Column(name = "Base_Clock")
    private String baseClock;
	
	@Column(name = "Cache")
    private String cache;
	
	@Column(name = "Socket")
    private String socket;
	
	@Column(name = "Cpu_Cooler")
    private String cpuCooler;
	
	@Column(name = "TDP")
    private String tdp;
	
	@Column(name = "Integrated_GPU")
    private String integratedGpu;
}
