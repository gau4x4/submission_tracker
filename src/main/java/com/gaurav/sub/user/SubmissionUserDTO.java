package com.gaurav.sub.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubmissionUserDTO {
    @Id
    private Long userId;
    private String email;
    private String consultantName;
    private List<String> roles;
}
