package com.dotjson.controller;

import com.dotjson.entity.InterviewRequest;
import com.dotjson.entity.InterviewResponse;
import com.dotjson.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
public class InterviewController {

    @Autowired
    private InterviewService service;

    @GetMapping
    public String greetingMessage() {
        return service.greeting();
    }

    @PostMapping("/save")
    public ResponseEntity<InterviewResponse> save(@RequestBody InterviewRequest interviewRequest) {
        return ResponseEntity.ok(service.saveInterview(interviewRequest));
    }

    @GetMapping("/getInterview")
    public ResponseEntity<InterviewResponse> getById(@RequestParam int id) {
        return ResponseEntity.ok(service.getInterview(id));
    }
}
