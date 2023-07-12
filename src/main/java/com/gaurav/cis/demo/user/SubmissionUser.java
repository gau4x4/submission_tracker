package com.gaurav.cis.demo.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SubmissionUser {
    @Id
    @Getter @Setter
    private String userId;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String consultantName;
    @Getter @Setter
    private List<String> roles;
    @Getter @Setter
    private String password;
    @PrePersist
    public void generateId() {
        String uniqueID = UUID.randomUUID().toString();
        this.userId =  uniqueID;
    }

}
