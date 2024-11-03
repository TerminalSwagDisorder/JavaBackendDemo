package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "memory")
public class Memory extends UniversalComponents {
    @Column(name = "Type")
    @JsonProperty("Type")
    private String type;

    @Column(name = "Amount")
    @JsonProperty("Amount")
    private String amount;

    @Column(name = "Speed")
    @JsonProperty("Speed")
    private String speed;

    @Column(name = "Latency")
    @JsonProperty("Latency")
    private String latency;
}