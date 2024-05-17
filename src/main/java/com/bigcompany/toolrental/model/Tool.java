package com.bigcompany.toolrental.model;

import lombok.*;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Tool {
    private Long id;
    private String code;
    private Long toolTypeId;
    private Long brandId;
    private Boolean deleted;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}
