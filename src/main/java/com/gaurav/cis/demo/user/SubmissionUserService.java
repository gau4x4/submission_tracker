package com.gaurav.cis.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SubmissionUserService {
    private final SubmissionUserRepository submissionUserRepository;
    @Autowired
    public SubmissionUserService(SubmissionUserRepository submissionUserRepository) {
        this.submissionUserRepository = submissionUserRepository;
    }

    public List<SubmissionUser> getSubUserDetails() {
        return submissionUserRepository.findAll();
    }

    public void addNewSubmission(SubmissionUser submissionUser) {
        final Optional<SubmissionUserDTO> submissionByEmail = this.submissionUserRepository.findSubmissionByEmail(submissionUser.getEmail());
        if (submissionByEmail.isPresent()) {
            throw new IllegalStateException("Email taken");
        }
        this.submissionUserRepository.save(submissionUser);
    }

    public void removeUserSubmission(String usedId) {
        final boolean exists = this.submissionUserRepository.existsById(usedId);
        if (!exists) {
            throw new IllegalStateException("User doesn't exists!");
        }
        this.submissionUserRepository.deleteById(usedId);
    }

    @Transactional
    public void updateStudent(String userId, String name) throws Throwable {
        SubmissionUser submissionUserDTO =  submissionUserRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException("User with id: " + userId + " doesn't exists!"));
        if (name != null && name.length() > 0 && !Objects.equals(submissionUserDTO.getConsultantName(), name)) {
            submissionUserDTO.setConsultantName(name);
        }
    }
}
