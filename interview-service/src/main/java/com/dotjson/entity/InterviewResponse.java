package com.dotjson.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class InterviewResponse {

    private int id;
    private String time;
    private String userId;
    private String jobId;
}
