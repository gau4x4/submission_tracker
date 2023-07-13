package com.gaurav.sub.submission;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class SubmissionDTO {
    @Id
//    @GenericGenerator(
//            name = "submission_sequence",
//            strategy = "com.gaurav.cis.demo.submission.SubmissionSequenceGenerator"
//    )
//    @GeneratedValue(
//            generator = "submission_sequence"
//    )
    @Getter
    @Setter
    private String submissionId;
    @Getter
    @Setter
    private LocalDate submissionDate;
    @Getter
    @Setter
    private String consultantName;
    @Getter
    @Setter
    private String vendorCompany;
    @Getter
    @Setter
    private String vendorName;
    @Getter
    @Setter
    private String email;
    @Getter
    @Setter
    private String phoneNo;
    @Getter
    @Setter
    private String implementationPartner;
    @Getter
    @Setter
    private String clientName;
    @Getter
    @Setter
    private double payRate;
    @Getter
    @Setter
    private boolean isSubmitted;
    @Getter
    @Setter
    private String ownerType;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private String leadName;
    @PrePersist
    public void generateId() {
        String uniqueID = UUID.randomUUID().toString();
        this.submissionId = "cis" + LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE).toString() + uniqueID;
    }
    public SubmissionDTO(LocalDate submissionDate, String consultantName, String vendorCompany, String vendorName, String email, String phoneNo, String implementationPartner, String clientName, double payRate, boolean isSubmitted, String ownerType, String status, String leadName) {
        this.submissionDate = submissionDate;
        this.consultantName = consultantName;
        this.vendorCompany = vendorCompany;
        this.vendorName = vendorName;
        this.email = email;
        this.phoneNo = phoneNo;
        this.implementationPartner = implementationPartner;
        this.clientName = clientName;
        this.payRate = payRate;
        this.isSubmitted = isSubmitted;
        this.ownerType = ownerType;
        this.status = status;
        this.leadName = leadName;
    }


//    @Override
//    public String toString() {
//        return "SubmissionDTO{" +
//                "submissionId='" + submissionId + '\'' +
//                ", submissionDate=" + submissionDate +
//                ", vendorCompany='" + vendorCompany + '\'' +
//                ", vendorName='" + vendorName + '\'' +
//                ", email='" + email + '\'' +
//                ", phoneNo='" + phoneNo + '\'' +
//                ", implementationPartner='" + implementationPartner + '\'' +
//                ", clientName='" + clientName + '\'' +
//                ", payRate=" + payRate +
//                ", isSubmitted=" + isSubmitted +
//                ", ownerType='" + ownerType + '\'' +
//                ", status='" + status + '\'' +
//                ", leadName='" + leadName + '\'' +
//                '}';
//    }
}
