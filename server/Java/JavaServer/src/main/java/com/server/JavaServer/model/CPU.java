package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cpu")
public class CPU extends UniversalComponents {
	@Column(name = "Core_Count")
	@JsonProperty("Core_Count")
    private String coreCount;
	
	@Column(name = "Thread_Count")
	@JsonProperty("Thread_Count")
    private String threadCount;
	
	@Column(name = "Base_Clock")
	@JsonProperty("Base_Clock")
    private String baseClock;
	
	@Column(name = "Cache")
	@JsonProperty("Cache")
    private String cache;
	
	@Column(name = "Socket")
	@JsonProperty("Socket")
    private String socket;
	
	@Column(name = "Cpu_Cooler")
	@JsonProperty("Cpu_Cooler")
    private String cpuCooler;
	
	@Column(name = "TDP")
	@JsonProperty("TDP")
    private String tdp;
	
	@Column(name = "Integrated_GPU")
	@JsonProperty("Integrated_GPU")
    private String integratedGpu;
}
