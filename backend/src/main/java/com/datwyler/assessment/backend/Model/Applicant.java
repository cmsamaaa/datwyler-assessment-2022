package com.datwyler.assessment.backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Applicant {
	
	private int applicantId;
	private String nric;
	private String firstName;
	private String lastName;
	
	public Applicant() {
		
	}
	
	public Applicant(int applicantId) {
		this.applicantId = applicantId;
	}

	public Applicant(int applicantId, String nric, String firstName, String lastName) {
		this.applicantId = applicantId;
		this.nric = nric;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getApplicantId() {
		return applicantId;
	}

	public String getNric() {
		return nric;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return "Applicant [applicantId=" + this.getApplicantId() + ", nric=" + this.getNric() + ", firstName=" + this.getFirstName() + ", lastName="
				+ this.getLastName() + "]";
	}
	
	// Database Access
	SQLiteJDBC dbConn = new SQLiteJDBC();
	
	public List<Applicant> RetrieveAllApplicants() {
		ArrayList<Applicant> aList = new ArrayList<Applicant>(); 
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = dbConn.GetConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM applicants");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				aList.add(new Applicant(rs.getInt("applicantId"), rs.getString("nric"), rs.getString("firstName"), rs.getString("lastName")));
			}
			
			dbConn.CloseConnection(conn);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return aList.size() > 0 ? aList : null;
	}

}
