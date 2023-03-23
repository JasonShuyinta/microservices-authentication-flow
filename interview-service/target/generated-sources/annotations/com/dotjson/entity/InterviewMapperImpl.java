package com.dotjson.entity;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-23T19:40:00+0100",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 17.0.5 (Oracle Corporation)"
)
@Component
public class InterviewMapperImpl implements InterviewMapper {

    @Override
    public InterviewResponse interviewToInterviewResponse(Interview interview) {
        if ( interview == null ) {
            return null;
        }

        InterviewResponse interviewResponse = new InterviewResponse();

        interviewResponse.setId( interview.getId() );
        interviewResponse.setTime( interview.getTime() );
        interviewResponse.setUserId( interview.getUserId() );
        interviewResponse.setJobId( interview.getJobId() );

        return interviewResponse;
    }

    @Override
    public Interview interviewRequestToInterview(InterviewRequest request) {
        if ( request == null ) {
            return null;
        }

        Interview interview = new Interview();

        interview.setId( request.getId() );
        interview.setTime( request.getTime() );
        interview.setUserId( request.getUserId() );
        interview.setJobId( request.getJobId() );

        return interview;
    }
}
