package com.aathif.its.task.dto;

import com.aathif.its.task.model.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskDTO {
    private String title;
    private String description;
    private LocalDate date;
    private Status status;
}
