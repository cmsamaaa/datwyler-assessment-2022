package com.datwyler.assessment.backend.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.datwyler.assessment.backend.Model.Applicant;
import com.datwyler.assessment.backend.Model.CreditFacility;
import com.datwyler.assessment.backend.Model.Loan;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class BankController {
	
	// Needed functionalities
	
	// 1. Bank can open new credit facility for each applicant
	@PostMapping(path = "/create/creditFacility")
	public boolean createCreditFacility(@RequestBody Applicant applicant) {
		CreditFacility cf = new CreditFacility(applicant.getApplicantId());
		return cf.InsertCreditFacility();
	}
	
	// 2. Bank can give new loans to the applicant under that credit facility
	@PostMapping(path = "/create/loan")
	public boolean getUser(@RequestBody Loan loan) {		
		if (loan.getType().equals("Home") || loan.getType().equals("Car"))
			return loan.InsertLoan();
		else
			return false;
	}
	
	// 3. Bank can monitor all loans for each applicant
	@GetMapping(path = "/get/loan/all/{applicantId}")
	public List<Loan> getAllLoans(@PathVariable("applicantId") int applicantId) {
		Loan loan = new Loan();
		return loan.RetrieveLoansByApplicantId(applicantId);
	}

	
	// Support functionalities
	
	// Get List of Applicants
	@GetMapping(path = "/get/applicant/all")
	public List<Applicant> getAllApplicants() {
		Applicant applicant = new Applicant();
		
		return applicant.RetrieveAllApplicants();
	}

}
