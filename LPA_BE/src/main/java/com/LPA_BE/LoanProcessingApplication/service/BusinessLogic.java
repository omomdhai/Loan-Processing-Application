package com.LPA_BE.LoanProcessingApplication.service;

import com.LPA_BE.LoanProcessingApplication.entity.Application;
import com.LPA_BE.LoanProcessingApplication.entity.BureauData;
import com.LPA_BE.LoanProcessingApplication.repository.BureaDataRepository;
import com.LPA_BE.LoanProcessingApplication.repository.Crud;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

@Service
public class BusinessLogic {

    @Autowired
    private Crud applicationRepository;

    @Autowired
    private BureaDataRepository bureaDataRepository;

    private static final double LOAN_AMOUNT_MIN = 500.0;
    private static final double LOAN_AMOUNT_MAX = 35000.0;
    private static final double EMP_LENGTH_MIN = 1.0;
    private static final double EMP_LENGTH_MAX = 10.0;
    private static final double ANNUAL_INC_MIN = 6000.0;
    private static final double ANNUAL_INC_MAX = 2039784.0;
    private static final double DELINQ_2YRS_MIN = 0.0;
    private static final double DELINQ_2YRS_MAX = 8.0;
    private static final double INQ_LAST_6MTHS_MIN = 0.0;
    private static final double INQ_LAST_6MTHS_MAX = 8.0;
    private static final double MTHS_SINCE_LAST_DELQ_MIN = 0.0;
    private static final double MTHS_SINCE_LAST_DELQ_MAX = 120.0;
    private static final double MTHS_SINCE_LAST_RECORD_MIN = 0.0;
    private static final double MTHS_SINCE_LAST_RECORD_MAX = 130.0;
    private static final double OPEN_ACC_MIN = 2.0;
    private static final double OPEN_ACC_MAX = 42.0;
    private static final double PUB_REC_MIN = 0.0;
    private static final double PUB_REC_MAX = 4.0;
    private static final double REVOL_BAL_MIN = 0.0;
    private static final double REVOL_BAL_MAX = 149527.0;
    private static final double REVOL_UTIL_MIN = 0.0;
    private static final double REVOL_UTIL_MAX = 1.0;
    private static final double TOTAL_ACC_MIN = 2.0;
    private static final double TOTAL_ACC_MAX = 81.0;
    private static final double DEBT_TO_INCOME_RATIO_MIN = 0.0;
    private static final double DEBT_TO_INCOME_RATIO_MAX = 3.103233;
    private static final double AGE_ON_FILE_MIN = 192.0;
    private static final double AGE_ON_FILE_MAX = 896.0;
    private static final double EMPLOYMENT_LENGTH_TO_INCOME_MIN = 4.902480e-07;
    private static final double EMPLOYMENT_LENGTH_TO_INCOME_MAX = 1.236400e-03;
    private static final double REVOL_BAL_TO_LOAN_AMNT_RATIO_MIN = 0.0;
    private static final double REVOL_BAL_TO_LOAN_AMNT_RATIO_MAX = 70.31385;
    private static final double CREDIT_UTILIZATION_RATE_MIN = 0.0;
    private static final double CREDIT_UTILIZATION_RATE_MAX = 0.01;
    private static final double OPEN_ACC_TO_TOTAL_ACC_RATIO_MIN = 0.06896552;
    private static final double OPEN_ACC_TO_TOTAL_ACC_RATIO_MAX = 1.75;
    private static final double CREDIT_HISTORY_LENGTH_MIN = 2013.167;
    private static final double CREDIT_HISTORY_LENGTH_MAX = 2024.0;
    private static final double LOAN_AMNT_ANNUAL_INC_MIN = 6300000.0;
    private static final double LOAN_AMNT_ANNUAL_INC_MAX = 2.250000e+10;
    private static final double EMP_LENGTH_ANNUAL_INC_MIN = 6000.0;
    private static final double EMP_LENGTH_ANNUAL_INC_MAX = 12258000.0;
    private static final double REVOL_BAL_TOTAL_ACC_MIN = 0.0;
    private static final double REVOL_BAL_TOTAL_ACC_MAX = 8184448.0;
    private static final double DELINQ_LAST_2YRS_BINARY_MIN = 0.0;
    private static final double DELINQ_LAST_2YRS_BINARY_MAX = 1.0;
    private static final double RECENT_DELINQ_MIN = 0.0;
    private static final double RECENT_DELINQ_MAX = 1.0;
    private static final double MULTIPLE_INQUIRIES_LAST_6MTHS_MIN = 0.0;
    private static final double MULTIPLE_INQUIRIES_LAST_6MTHS_MAX = 1.0;
    private static final double PURPOSE_BINARY_MIN = 0.0;
    private static final double PURPOSE_BINARY_MAX = 1.0;
    private static final double ADDED_MIN = 0.0;
    private static final double ADDED_MAX = 1.0;
    private static final double BORROWER_MIN = 0.0;
    private static final double BORROWER_MAX = 1.0;
    private static final double BR_MIN = 0.0;
    private static final double BR_MAX = 0.9949024;
    private static final double CARD_MIN = 0.0;
    private static final double CARD_MAX = 1.0;
    private static final double CARDS_MIN = 0.0;
    private static final double CARDS_MAX = 1.0;
    private static final double CREDIT_MIN = 0.0;
    private static final double CREDIT_MAX = 1.0;
    private static final double DEBT_MIN = 0.0;
    private static final double DEBT_MAX = 1.0;
    private static final double LOAN_MIN = 0.0;
    private static final double LOAN_MAX = 1.0;
    private static final double PAY_MIN = 0.0;
    private static final double PAY_MAX = 1.0;
    private static final double YEARS_MIN = 0.0;
    private static final double YEARS_MAX = 1.0;


    /*   */
    private static double minMaxScale(double value, double min, double max) {
        double x = (double)((value - min) / (double)(max - min));
        return (double)(x);
    }

    private static double calculateAgeOnFile(String earliestCrLine) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy H:mm");
        LocalDateTime earliestDate = LocalDateTime.parse(earliestCrLine, formatter);
        LocalDateTime now = LocalDateTime.now();
        return ChronoUnit.MONTHS.between(earliestDate, now);
    }


    public static double convertPurposeToBinary(String purpose) {
        String[] favorablePurposes = { "home_improvement", "small_business",
                "car", "wedding", "medical", "house", "moving", "educational", "renewable_energy"};
        for (String favorablePurpose : favorablePurposes) {
            if (purpose.equals(favorablePurpose)) {
                return 1.0;
            }
        }
        return 0.0;
    }

    public static double checkKeywordPresence(String description) {
        if(Objects.equals(description, "")){
            return 0.0;
        }
        String[] keywords = {"added", "borrower", "br", "card", "cards", "credit", "debt", "loan", "pay", "years"};
        double[] weights = {
                -0.6151, // added
                -0.4153, // borrower
                0.2931,  // br
                0.5743,  // card
                -0.2942, // cards
                0.6117,  // credit
                -0.0606, // debt
                -0.0274, // loan
                -0.1934, // pay
                0.1155   // years
        };
        double keywordScore = 0.0;

        for (int i = 0; i < keywords.length; i++) {
            if (description.toLowerCase().contains(keywords[i])) {
                keywordScore += weights[i];
            }
        }
        return keywordScore;
    }




        public Double calcScore(Long ssnNumber, Application application) {
        BureauData bureauData = bureaDataRepository.getById(ssnNumber);
        double loanAmount = application.getLoanAmount();
        double workExperienceYear = application.getworkExperienceYears();
        double annualSalary = application.getAnnualSalary();
        double delinq_2yrs = bureauData.getDelinq_2yrs();
        double inq_last_6mths = bureauData.getInq_last_6mths();
        double mths_since_last_delinq = bureauData.getMths_since_last_delinq();
        double mths_since_last_record = bureauData.getMths_since_last_record();
        double open_acc = bureauData.getOpen_acc();
        double pub_rec = bureauData.getPub_rec();
        double revol_bal = bureauData.getRevol_bal();
        double revol_util = bureauData.getRevol_util();
        double total_acc = bureauData.getTotal_acc();
        double debtToIncomeRatio = loanAmount/annualSalary;
        String earliest_cr_line = bureauData.getEarliest_cr_line();
        String loanPurpose = application.getLoanPurpose();
        String description = application.getDescription();
        double employmentLengthToIncome = workExperienceYear / annualSalary;
        double ageOnFile = calculateAgeOnFile(earliest_cr_line);
        double revolBalToLoanAmntRatio = revol_bal / loanAmount;
        double creditUtilizationRate = revol_util / 100.0;
        double openAccToTotalAccRatio = open_acc / total_acc;
        double creditHistoryLength = 2024 - (mths_since_last_record / 12.0);
        double LoanAmount = loanAmount * annualSalary;
        double empLengthAnnualInc = workExperienceYear * annualSalary;
        double revolBalTotalAcc = revol_bal * total_acc;
        double delinqLast2yrsBinary = delinq_2yrs > 0 ? 1 : 0;
        double recentDelinq = mths_since_last_delinq < 12 ? 1 : 0;
        double multipleInquiriesLast6mths = inq_last_6mths > 2 ? 1 : 0;
        double purposeBinary = convertPurposeToBinary(loanPurpose);
        double desc_value = !description.isEmpty() ? checkKeywordPresence(description) : 0.0;

        ///
        loanAmount = minMaxScale(loanAmount, LOAN_AMOUNT_MIN, LOAN_AMOUNT_MAX);
        workExperienceYear = minMaxScale(workExperienceYear, EMP_LENGTH_MIN, EMP_LENGTH_MAX);
        annualSalary = minMaxScale(annualSalary, ANNUAL_INC_MIN, ANNUAL_INC_MAX);
        delinq_2yrs = minMaxScale(delinq_2yrs, DELINQ_2YRS_MIN, DELINQ_2YRS_MAX);
        inq_last_6mths = minMaxScale(inq_last_6mths, INQ_LAST_6MTHS_MIN, INQ_LAST_6MTHS_MAX);
        mths_since_last_delinq = minMaxScale(mths_since_last_delinq, MTHS_SINCE_LAST_DELQ_MIN, MTHS_SINCE_LAST_DELQ_MAX);
        mths_since_last_record = minMaxScale(mths_since_last_record, MTHS_SINCE_LAST_RECORD_MIN, MTHS_SINCE_LAST_RECORD_MAX);
        open_acc = minMaxScale(open_acc, OPEN_ACC_MIN, OPEN_ACC_MAX);
        pub_rec = minMaxScale(pub_rec, PUB_REC_MIN, PUB_REC_MAX);
        revol_bal = minMaxScale(revol_bal, REVOL_BAL_MIN, REVOL_BAL_MAX);
        revol_util = minMaxScale(revol_util, REVOL_UTIL_MIN, REVOL_UTIL_MAX);
        total_acc = minMaxScale(total_acc, TOTAL_ACC_MIN, TOTAL_ACC_MAX);
        debtToIncomeRatio = minMaxScale(debtToIncomeRatio, DEBT_TO_INCOME_RATIO_MIN, DEBT_TO_INCOME_RATIO_MAX);
        ageOnFile = minMaxScale(ageOnFile, AGE_ON_FILE_MIN, AGE_ON_FILE_MAX);
        employmentLengthToIncome = minMaxScale(employmentLengthToIncome, EMPLOYMENT_LENGTH_TO_INCOME_MIN, EMPLOYMENT_LENGTH_TO_INCOME_MAX);
        revolBalToLoanAmntRatio = minMaxScale(revolBalToLoanAmntRatio, REVOL_BAL_TO_LOAN_AMNT_RATIO_MIN, REVOL_BAL_TO_LOAN_AMNT_RATIO_MAX);
        creditUtilizationRate = minMaxScale(creditUtilizationRate, CREDIT_UTILIZATION_RATE_MIN, CREDIT_UTILIZATION_RATE_MAX);
        openAccToTotalAccRatio = minMaxScale(openAccToTotalAccRatio, OPEN_ACC_TO_TOTAL_ACC_RATIO_MIN, OPEN_ACC_TO_TOTAL_ACC_RATIO_MAX);
        creditHistoryLength = minMaxScale(creditHistoryLength, CREDIT_HISTORY_LENGTH_MIN, CREDIT_HISTORY_LENGTH_MAX);
        LoanAmount = minMaxScale(LoanAmount, LOAN_AMNT_ANNUAL_INC_MIN, LOAN_AMNT_ANNUAL_INC_MAX);
        empLengthAnnualInc = minMaxScale(empLengthAnnualInc, EMP_LENGTH_ANNUAL_INC_MIN, EMP_LENGTH_ANNUAL_INC_MAX);
        revolBalTotalAcc = minMaxScale(revolBalTotalAcc, REVOL_BAL_TOTAL_ACC_MIN, REVOL_BAL_TOTAL_ACC_MAX);
        delinqLast2yrsBinary = minMaxScale(delinqLast2yrsBinary, DELINQ_LAST_2YRS_BINARY_MIN, DELINQ_LAST_2YRS_BINARY_MAX);
        recentDelinq = minMaxScale(recentDelinq, RECENT_DELINQ_MIN, RECENT_DELINQ_MAX);
        multipleInquiriesLast6mths = minMaxScale(multipleInquiriesLast6mths, MULTIPLE_INQUIRIES_LAST_6MTHS_MIN, MULTIPLE_INQUIRIES_LAST_6MTHS_MAX);


        double logitP = 2.6769
                + (-1.2864 * loanAmount)
                + (0.3067 * workExperienceYear)
                + (1.1572 * annualSalary)
                + (-1.0738 * delinq_2yrs)
                + (-1.8304 * inq_last_6mths)
                + (0.4222 * mths_since_last_delinq)
                + (0.6419 * mths_since_last_record)
                + (-0.4192 * open_acc)
                + (-1.5078 * pub_rec)
                + (1.2807 * revol_bal)
                + (-0.7212 * revol_util)
                + (0.0088 * total_acc)
                + (-1.2665 * debtToIncomeRatio)
                + (-0.0490 * ageOnFile)
                + (-2.4112 * employmentLengthToIncome)
                + (-0.1717 * revolBalToLoanAmntRatio)
                + (-0.7212 * creditUtilizationRate)
                + (-1.0946 * openAccToTotalAccRatio)
                + (0.8489 * creditHistoryLength)
                + (1.1591 * LoanAmount)
                + (0.8236 * empLengthAnnualInc)
                + (-0.1080 * revolBalTotalAcc)
                + (-6.7274 * delinqLast2yrsBinary)
                + (-0.3133 * recentDelinq)
                + (-6.5223 * multipleInquiriesLast6mths)
                + (-0.2743 * purposeBinary)
                + (desc_value);
        double p = 1 / (1 + Math.exp(-logitP));
        String status = p >= 0.5 ? "Approved" : "Declined";
        String reason = status == "Declined" ? "Low Credit Score" : "None";
        double creditScore = (p)*(1000.0);
        double rangedCreditScore =   350 + ((creditScore - 0) * (850- 350)) / (1000);
        return (double)rangedCreditScore;
    }
}
