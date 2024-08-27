package com.LPA_BE.LoanProcessingApplication.service;

import com.LPA_BE.LoanProcessingApplication.dto.ApplicationSummaryDTO;
import com.LPA_BE.LoanProcessingApplication.entity.Application;
import com.LPA_BE.LoanProcessingApplication.exception.DuplicateResourceException;
import com.LPA_BE.LoanProcessingApplication.exception.ResourceNotFoundException;
import com.LPA_BE.LoanProcessingApplication.repository.Crud;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ApplicationService {

    private final Crud repository;

   @Autowired
   public ApplicationService(Crud repository) {
       this.repository = repository;
  }


  public List<ApplicationSummaryDTO> getApplicationSummaries() {
        return repository.findAll().stream()
                .map(this::convertToSummaryDTO)
                .collect(Collectors.toList());
    }

    private ApplicationSummaryDTO convertToSummaryDTO(Application application) {
        String applicantName = application.getFirstName() + " " + application.getMiddleName() + " " + application.getLastName();
        return new ApplicationSummaryDTO(
                application.getId(),
                applicantName,
                application.getSubmittedDate(),
                application.getApplicationStatus()
        );
    }


        public List<Application> getAllApplicants() {
            return repository.findAll();
        }

        public Application getApplicantById(Long id) {
            return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Application not found with id: " + id));
        }

    public Application getApplicantBySsnNumber(Long ssnNumber) {
        return repository.findBySsnNumber(ssnNumber)
                .orElseThrow(() -> new RuntimeException("Application not found with this SSN Number"));
    }

    //public Application getApplicantBySsnNumber(String ssnNumber){return repository.findBySsnNumber(ssnNumber);}

        public Application createApplicant(Application applicant) {
            try {
                return repository.save(applicant);
            } catch (DataIntegrityViolationException e) {
                throw new DuplicateResourceException("SSN number must be unique. This SSN is already in use.");
            }
    }
    }


