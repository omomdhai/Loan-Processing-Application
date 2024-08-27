package com.LPA_BE.LoanProcessingApplication.service;

import com.LPA_BE.LoanProcessingApplication.entity.Application;
import com.LPA_BE.LoanProcessingApplication.entity.BureauData;
import com.LPA_BE.LoanProcessingApplication.repository.BureaDataRepository;
import com.LPA_BE.LoanProcessingApplication.repository.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BureaDataService {

    @Autowired
    private Crud applicationRepository;

    @Autowired
    private BureaDataRepository bureauDataRepository;

//    public void calculateScore(Long ssnNumber) {
//        Application application = applicationRepository.findBySsnNumber(ssnNumber).orElseThrow();
//        BureauData bureauData = bureauDataRepository.findById(ssnNumber).orElseThrow();
//
//
//
//        // Update application with score and description
//       application.setScore(score);
//       application.setDescription(description);
//
//        applicationRepository.save(application);
//    }

    public List<BureauData> findAll() {
        return bureauDataRepository.findAll();
    }

    public BureauData findById(Long id) {
        return bureauDataRepository.findById(id).orElse(null);
    }

    public BureauData save(BureauData bureauData) {
        return bureauDataRepository.save(bureauData);
    }

    public void deleteById(Long id) {
        bureauDataRepository.deleteById(id);
    }

}
