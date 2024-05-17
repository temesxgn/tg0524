package com.bigcompany.toolrental.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Brand {
    private Long id;
    private String name;
    private Boolean deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
