package com.dotjson.service;

import com.dotjson.entity.Interview;
import com.dotjson.entity.InterviewMapper;
import com.dotjson.entity.InterviewRequest;
import com.dotjson.entity.InterviewResponse;
import com.dotjson.repository.InterviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewService {
    public static final String WELCOME_TO_INTEVIEW_SERVICE = "Welcome to Inteview service";

    private final InterviewRepository interviewRepository;
    private final InterviewMapper interviewMapper;

    public String greeting() {
        return WELCOME_TO_INTEVIEW_SERVICE;
    }

    public InterviewResponse saveInterview(InterviewRequest interviewRequest) {
        Interview interview = interviewRepository.save(interviewMapper.interviewRequestToInterview(interviewRequest));
        return interviewMapper.interviewToInterviewResponse(interview);
    }

    public InterviewResponse getInterview(int id) {
        Interview interview = interviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Could not find interview slot"));
        return interviewMapper.interviewToInterviewResponse(interview);
    }
}
