package com.LPA_BE.LoanProcessingApplication.controller;

import com.LPA_BE.LoanProcessingApplication.entity.BureauData;
import com.LPA_BE.LoanProcessingApplication.service.BureaDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/bureau_data")
public class BureauDataController {

    @Autowired
    private BureaDataService bureauDataService;

    @GetMapping
    public List<BureauData> getAllBureauData() {
        return bureauDataService.findAll();
    }

    @GetMapping("/{id}")
    public BureauData getBureauDataById(@PathVariable Long id) {
        return bureauDataService.findById(id);
    }

//    @PostMapping
//    public BureauData createBureauData(@RequestBody BureauData bureauData) {
//        return bureauDataService.save(bureauData);
//    }

    @DeleteMapping("/{id}")
    public void deleteBureauData(@PathVariable Long id) {
        bureauDataService.deleteById(id);
    }
}
