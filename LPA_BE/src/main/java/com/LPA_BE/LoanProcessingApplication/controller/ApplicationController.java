package com.LPA_BE.LoanProcessingApplication.controller;

import com.LPA_BE.LoanProcessingApplication.dto.ApplicationSummaryDTO;
import com.LPA_BE.LoanProcessingApplication.entity.Application;
import com.LPA_BE.LoanProcessingApplication.repository.BureaDataRepository;
import com.LPA_BE.LoanProcessingApplication.repository.Crud;
import com.LPA_BE.LoanProcessingApplication.service.ApplicationService;
import com.LPA_BE.LoanProcessingApplication.service.BusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.LPA_BE.LoanProcessingApplication.service.BureaDataService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/applications")
//@Component
public class ApplicationController {

    private final ApplicationService service;

    @Autowired
    private BusinessLogic businessLogic;

    @Autowired
    public ApplicationController(ApplicationService service)
    {
        this.service = service;
    }

    @GetMapping("/view_apps")
    public List<ApplicationSummaryDTO> getApplicationSummaries() {
        return service.getApplicationSummaries();
    }

    @GetMapping("/view_application")
    public List<Application> getAllApplicants() {
        return service.getAllApplicants();
    }

    @GetMapping("view_application/{id}")
    public Application getApplicantById(@PathVariable Long id) {
        return service.getApplicantById(id);
    }

    @PostMapping
    public Application createApplicant(@RequestBody Application applicant) {
        // here ::
        Long ssnNumber = applicant.getSsnNumber();
        Long id = applicant.getId();
        System.out.println("ssnNumber : " +  ssnNumber);
        double creditScore = businessLogic.calcScore(ssnNumber, applicant);
        //

        //
        applicant.setScore(creditScore);
        String declineReason = creditScore >=600 ? "None" : "Low Credit Score";
        String applicationStatus = creditScore>=600 ? "Approved" : "Declined";
        applicant.setDeclineReason(declineReason);
        applicant.setApplicationStatus(applicationStatus);
//        applicationRepository.save(application);
//        return applicationRepository.getById(id);
        return service.createApplicant(applicant);
    }
}
