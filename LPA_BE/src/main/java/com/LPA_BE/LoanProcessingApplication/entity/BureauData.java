package com.LPA_BE.LoanProcessingApplication.entity;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "bureau_data")
public class BureauData {

    @Id
    private String id;
    private Integer delinq_2yrs;
    private Integer inq_last_6mths;
    private Integer mths_since_last_delinq;
    private Integer mths_since_last_record;
    private Integer open_acc;
    private Integer pub_rec;
    private Long revol_bal;
    private Double revol_util;
    private Integer total_acc;
    private String earliest_cr_line;

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getDelinq_2yrs() {
        return delinq_2yrs;
    }

    public void setDelinq_2yrs(Integer delinq_2yrs) {
        this.delinq_2yrs = delinq_2yrs;
    }

    public Integer getInq_last_6mths() {
        return inq_last_6mths;
    }

    public void setInq_last_6mths(Integer inq_last_6mths) {
        this.inq_last_6mths = inq_last_6mths;
    }

    public Integer getMths_since_last_delinq() {
        return mths_since_last_delinq;
    }

    public void setMths_since_last_delinq(Integer mths_since_last_delinq) {
        this.mths_since_last_delinq = mths_since_last_delinq;
    }

    public Integer getMths_since_last_record() {
        return mths_since_last_record;
    }

    public void setMths_since_last_record(Integer mths_since_last_record) {
        this.mths_since_last_record = mths_since_last_record;
    }

    public Integer getOpen_acc() {
        return open_acc;
    }

    public void setOpen_acc(Integer open_acc) {
        this.open_acc = open_acc;
    }

    public Integer getPub_rec() {
        return pub_rec;
    }

    public void setPub_rec(Integer pub_rec) {
        this.pub_rec = pub_rec;
    }

    public Long getRevol_bal() {
        return revol_bal;
    }

    public void setRevol_bal(Long revol_bal) {
        this.revol_bal = revol_bal;
    }

    public Double getRevol_util() {
        return revol_util;
    }

    public void setRevol_util(Double revol_util) {
        this.revol_util = revol_util;
    }

    public Integer getTotal_acc() {
        return total_acc;
    }

    public void setTotal_acc(Integer total_acc) {
        this.total_acc = total_acc;
    }

    public String getEarliest_cr_line() {
        return earliest_cr_line;
    }

    public void setEarliest_cr_line(String earliest_cr_line) {
        this.earliest_cr_line = earliest_cr_line;
    }
}

