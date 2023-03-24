package com.dotjson.controller;

import com.dotjson.entity.JobRequest;
import com.dotjson.entity.JobResponse;
import com.dotjson.service.JobSearchService;
import com.dotjson.validation.JobNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/job")
public class JobSearchController {

    @Autowired
    private JobSearchService jobSearchService;

    @GetMapping("/home")
    public String greetingMessage() {
        return jobSearchService.greeting();
    }

    @PostMapping("/save")
    public ResponseEntity<JobResponse> saveJob(@RequestBody @Valid JobRequest jobRequest) {
        return ResponseEntity.ok(jobSearchService.saveJob(jobRequest));
    }

    @GetMapping("/search")
    public ResponseEntity<JobResponse> getJob(@RequestParam String title) {
        return ResponseEntity.ok(jobSearchService.getJobByTitle(title));
    }

    @GetMapping("/fetchJobById")
    public ResponseEntity<JobResponse> fetchJobById(@RequestParam int id) throws JobNotFoundException {
        return ResponseEntity.ok(jobSearchService.getJobById(id));
    }

    @GetMapping("/fetchAll")
    public ResponseEntity<List<JobResponse>> getAll() {
        return ResponseEntity.ok(jobSearchService.getAll());
    }


}
