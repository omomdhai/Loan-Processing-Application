package com.LPA_BE.LoanProcessingApplication.dto;

public class ScoreDTO {
    private Double score;
    private String applicationStatus;
    private String declineReason;

    //constructor
    public ScoreDTO(Double score,String applicationStatus,String declineReason){
        this.score=score;
        this.applicationStatus=applicationStatus;
        this.declineReason=declineReason;
    }


    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getDeclineReason() {
        return declineReason;
    }

    public void setDeclineReason(String declineReason) {
        this.declineReason = declineReason;
    }
}
