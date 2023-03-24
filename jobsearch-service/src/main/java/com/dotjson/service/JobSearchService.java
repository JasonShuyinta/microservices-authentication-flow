package com.dotjson.service;

import com.dotjson.entity.Job;
import com.dotjson.entity.JobMapper;
import com.dotjson.entity.JobRequest;
import com.dotjson.entity.JobResponse;
import com.dotjson.repository.JobRepository;
import com.dotjson.validation.JobNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobSearchService {

    public static final String WELCOME_TO_JOB_SEARCH_SERVICE = "Welcome to Job Search Service";

    private final JobMapper mapper;
    private final JobRepository jobRepository;

    public String greeting() {
        return WELCOME_TO_JOB_SEARCH_SERVICE;
    }

    public JobResponse saveJob(JobRequest request) {
        Job job = jobRepository.save(mapper.jobRequestToEntity(request));
        return mapper.jobEntityToResponse(job);
    }

    public JobResponse getJobByTitle(String title) {
        Job job = jobRepository.findByTitle(title).orElseThrow(() -> new RuntimeException("Could not find job"));
        return mapper.jobEntityToResponse(job);
    }

    public JobResponse getJobById(int id) throws JobNotFoundException {
        Job job = jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Could find job with id " + id));
        return mapper.jobEntityToResponse(job);
    }

    public List<JobResponse> getAll() {
        List<JobResponse> response = new ArrayList<>();
        List<Job> jobList = jobRepository.findAll();
        for (Job job:
             jobList) {
            response.add(mapper.jobEntityToResponse(job));
        }
        return response;
    }
}
