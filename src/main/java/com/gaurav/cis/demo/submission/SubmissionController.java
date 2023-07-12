package com.gaurav.cis.demo.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/submission")
public class SubmissionController {
    private final SubmissionService submissionService;

    @Autowired
    public SubmissionController(SubmissionService submissionService) {
        this.submissionService = submissionService;
    }


    @GetMapping
    public List<SubmissionDTO> getSubmissions() {
        return this.submissionService.getSubmissions();
    }

    @PostMapping
    public void addNewSubmission(@RequestBody SubmissionDTO submissionDTO) {
        this.submissionService.addNewSubmission(submissionDTO);
    }

    @DeleteMapping(path = "{submissionId}")
    public void deleteStudent(@PathVariable("submissionId") String submissionId) {
        this.submissionService.removeSubmission(submissionId);
    }

    @PutMapping(path = "{submissionId}")
    public void updateStudent(@PathVariable("submissionId") String submissionId,
                              @RequestParam(required = false) String name) {
        try {
            this.submissionService.updateStudent(submissionId, name);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
