package com.dotjson.entity;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T19:40:46+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public JobResponse jobEntityToResponse(Job job) {
        if ( job == null ) {
            return null;
        }

        JobResponse jobResponse = new JobResponse();

        jobResponse.setId( job.getId() );
        jobResponse.setTitle( job.getTitle() );
        jobResponse.setDescription( job.getDescription() );
        jobResponse.setSalary( job.getSalary() );

        return jobResponse;
    }

    @Override
    public Job jobRequestToEntity(JobRequest request) {
        if ( request == null ) {
            return null;
        }

        Job job = new Job();

        job.setId( request.getId() );
        job.setTitle( request.getTitle() );
        job.setDescription( request.getDescription() );
        job.setSalary( request.getSalary() );

        return job;
    }
}
