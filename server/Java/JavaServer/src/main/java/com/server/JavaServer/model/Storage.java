package com.server.JavaServer.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "storage")
public class Storage extends UniversalComponents {
    @Column(name = "Capacity")
    private String capacity;

    @Column(name = "Form_Factor")
    private String formFactor;

    @Column(name = "Interface")
    private String interfaceType;

    @Column(name = "Cache")
    private String cache;

    @Column(name = "Flash")
    private String flash;

    @Column(name = "TBW")
    private String tbw;
}