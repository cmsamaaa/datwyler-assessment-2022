package com.datwyler.assessment.backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CreditFacility {

	private int creditId;
	private int applicantId;
	private Applicant applicant;

	public CreditFacility() {
		
	}
	
	public CreditFacility(int applicantId) {
		this.applicantId = applicantId;
	}
	
	public CreditFacility(int creditId, int applicantId) {
		this.creditId = creditId;
		this.applicantId = applicantId;
	}

	public CreditFacility(int creditId, int applicantId, String nric, String firstName, String lastName) {
		this.creditId = creditId;
		this.applicantId = applicantId;
		this.applicant = new Applicant(applicantId, nric, firstName, lastName);
	}

	public int getCreditId() {
		return creditId;
	}

	public int getApplicantId() {
		return applicantId;
	}
	
	public Applicant getApplicant() {
		return applicant;
	}

	@Override
	public String toString() {
		return "CreditFacility [creditId=" + this.getCreditId() + ", applicantId=" + this.getApplicantId() + "]";
	}
	
	// Database Access
	SQLiteJDBC dbConn = new SQLiteJDBC();
	
	public boolean InsertCreditFacility() {
		boolean result = false;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = dbConn.GetConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO creditFacilities (applicantId) VALUES (?)");
			stmt.setInt(1, this.getApplicantId());
			
			if(stmt.executeUpdate() > 0)
				result = true;
			
			dbConn.CloseConnection(conn);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return result;
	}
	
	public List<CreditFacility> RetrieveCreditFacilities() {
		ArrayList<CreditFacility> aList = new ArrayList<CreditFacility>(); 
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = dbConn.GetConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM creditFacilities cf INNER JOIN applicants a ON cf.applicantId = a.applicantId");
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				aList.add(new CreditFacility(rs.getInt("creditId"), rs.getInt("applicantId"), rs.getString("nric"), rs.getString("firstName"), rs.getString("lastName")));
			}
			
			dbConn.CloseConnection(conn);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return aList.size() > 0 ? aList : null;
	}
	
}
