package com.aathif.its.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestMetaDTO {
    private Long id;
    private String name;
    private String email;
    private String mobile;
}
