package com.dotjson.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobRequest {

    private int id;
    @NotNull(message = "Cannot have empty job title")
    private String title;
    @NotBlank(message = "Cannot leave blank description")
    private String description;
    @Min(100)
    private int salary;
    @Email(message = "Invalid email address")
    private String emailReference;
}
