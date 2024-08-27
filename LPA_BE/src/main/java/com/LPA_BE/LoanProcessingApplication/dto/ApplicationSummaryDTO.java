package com.LPA_BE.LoanProcessingApplication.dto;

import java.time.LocalDateTime;
public class ApplicationSummaryDTO {
    private Long id;
    private String applicantName;
    private LocalDateTime submittedDate;
    private String applicationStatus;

    // Constructors
    public ApplicationSummaryDTO(Long id, String applicantName, LocalDateTime submittedDate, String applicationStatus) {
        this.id = id;
        this.applicantName = applicantName;
        this.submittedDate = submittedDate;
        this.applicationStatus = applicationStatus;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public LocalDateTime getSubmittedDate() {
        return submittedDate;
    }

    public void setSubmittedDate(LocalDateTime submittedDate) {
        this.submittedDate = submittedDate;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }
}
