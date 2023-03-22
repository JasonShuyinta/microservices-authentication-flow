package com.dotjson.entity;


import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    InterviewResponse interviewToInterviewResponse(Interview interview);

    Interview interviewRequestToInterview(InterviewRequest request);
}
