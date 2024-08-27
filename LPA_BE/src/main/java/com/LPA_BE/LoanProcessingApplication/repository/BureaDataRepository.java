package com.LPA_BE.LoanProcessingApplication.repository;

import com.LPA_BE.LoanProcessingApplication.entity.Application;
import com.LPA_BE.LoanProcessingApplication.entity.BureauData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BureaDataRepository extends JpaRepository<BureauData,Long> {

}
