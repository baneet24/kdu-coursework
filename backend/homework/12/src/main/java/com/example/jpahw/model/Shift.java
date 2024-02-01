package com.example.jpahw.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Data
@Entity
@Table(name="shift")
public class Shift extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER)
    private ShiftType shiftType;

    @Column(length = 128)
    private String name;

    private LocalDate dateStart;

    private LocalDate dateEnd;

    private LocalTime timeStart;

    private LocalTime endTime;

    @Column(length = 32)
    private String timeZone;

    @ManyToOne
    @JoinColumn(name = "tenant_id")
    private Tenant tenant;

}
