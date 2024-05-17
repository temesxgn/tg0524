package com.bigcompany.toolrental.model;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Holiday {
    private Long id;
    private String name;
    private LocalDate date;
    private LocalDate observedDate;
    private int year;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

