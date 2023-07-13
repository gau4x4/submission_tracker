package com.gaurav.sub.submission;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubmissionService {
    private final SubmissionRepository submissionRepository;

    @Autowired
    public SubmissionService(SubmissionRepository submissionRepository) {
        this.submissionRepository = submissionRepository;
    }
    public List<SubmissionDTO> getSubmissions() {
        return submissionRepository.findAll();
    }

    public void addNewSubmission(SubmissionDTO submissionDTO) {
        final Optional<SubmissionDTO> submissionByEmail = this.submissionRepository.findSubmissionByEmail(submissionDTO.getEmail());
        if (submissionByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        this.submissionRepository.save(submissionDTO);
    }

    public void removeSubmission(String submissionId) {
        final boolean exists = this.submissionRepository.existsById(submissionId);
        if (!exists) {
            throw new IllegalStateException("Submission doesn't exists!");
        }
        this.submissionRepository.deleteById(submissionId);
    }

    @Transactional
    public void updateStudent(String submissionId, String name) throws Throwable {
        SubmissionDTO submissionDTO = (SubmissionDTO) submissionRepository.findById(submissionId).orElseThrow(() ->
                new IllegalStateException("Submission with id: " + submissionId + " doesn't exists!"));
        if (name != null && name.length() > 0 && !Objects.equals(submissionDTO.getConsultantName(), name)) {
            submissionDTO.setConsultantName(name);
        }
    }

}
