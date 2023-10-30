package com.aathif.its.task.dto;

import com.aathif.its.task.model.Status;
import com.aathif.its.task.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {
    private String title;
    private String description;
    private LocalDate date;
}
