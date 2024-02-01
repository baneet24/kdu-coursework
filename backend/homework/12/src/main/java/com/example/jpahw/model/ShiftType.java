package com.example.jpahw.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
@Table(name = "shift_type")
public class ShiftType extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private Type uniqueName;

    private String description;

    private boolean active;

    @Column(length = 32)
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

     public enum Type{
         MORNING,
         AFTERNOON,
         EVENING
    }
}