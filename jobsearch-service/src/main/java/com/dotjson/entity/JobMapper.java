package com.dotjson.entity;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface JobMapper {

    JobResponse jobEntityToResponse(Job job);

    Job jobRequestToEntity(JobRequest request);

}
