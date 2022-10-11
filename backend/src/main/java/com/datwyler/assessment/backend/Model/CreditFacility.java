package com.datwyler.assessment.backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class CreditFacility {

	private int creditId;
	private int applicantId;

	public CreditFacility() {
		
	}
	
	public CreditFacility(int applicantId) {
		this.applicantId = applicantId;
	}
	
	public CreditFacility(int creditId, int applicantId) {
		this.creditId = creditId;
		this.applicantId = applicantId;
	}

	public int getCreditId() {
		return creditId;
	}

	public int getApplicantId() {
		return applicantId;
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
	
}
