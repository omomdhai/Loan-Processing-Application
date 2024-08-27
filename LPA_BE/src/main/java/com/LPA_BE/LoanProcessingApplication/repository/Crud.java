package com.LPA_BE.LoanProcessingApplication.repository;

import com.LPA_BE.LoanProcessingApplication.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface Crud extends JpaRepository<Application,Long> {

   Optional<Application> findBySsnNumber(Long ssnNumber);
}
