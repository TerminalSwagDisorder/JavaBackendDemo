package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "memory")
public class Memory extends UniversalComponents {
    @Column(name = "Type")
    private String type;

    @Column(name = "Amount")
    private String amount;

    @Column(name = "Speed")
    private String speed;

    @Column(name = "Latency")
    private String latency;
}