package com.datwyler.assessment.backend.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Loan {

	private int loanId;
	private double amount;
	private String type;
	private String status;
	private int creditId;
	
	public Loan() {
		
	}
	
	public Loan(double amount, String type, int creditId) {
		this.amount = amount;
		this.type = type;
		this.creditId = creditId;
	}

	public Loan(int loanId, double amount, String type, String status, int creditId) {
		this.loanId = loanId;
		this.amount = amount;
		this.type = type;
		this.status = status;
		this.creditId = creditId;
	}

	public int getLoanId() {
		return loanId;
	}

	public double getAmount() {
		return amount;
	}

	public String getType() {
		return type;
	}
	
	public String getStatus() {
		return status;
	}

	public int getCreditId() {
		return creditId;
	}
	
	@Override
	public String toString() {
		return "Loan [loanId=" + this.getLoanId() + ", amount=" + this.getAmount() + ", type=" + this.getType() + ", status=" + this.getStatus() + ", creditId=" + this.getCreditId() + "]";
	}

	// Database Access
	SQLiteJDBC dbConn = new SQLiteJDBC();
	
	public boolean InsertLoan() {
		boolean result = false;
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = dbConn.GetConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO loans (amount, type, creditId) VALUES (?, ?, ?)");
			stmt.setDouble(1, this.getAmount());
			stmt.setString(2, this.getType());
			stmt.setInt(3, this.getCreditId());
			
			if(stmt.executeUpdate() > 0)
				result = true;
			
			dbConn.CloseConnection(conn);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return result;
	}
		
	public List<Loan> RetrieveLoansByApplicantId(int applicantId) {
		ArrayList<Loan> aList = new ArrayList<Loan>(); 
		
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = dbConn.GetConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM loans l INNER JOIN creditFacilities cf ON l.creditId = cf.creditId WHERE cf.applicantId = ?");
			stmt.setInt(1, applicantId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				aList.add(new Loan(rs.getInt("loanId"), rs.getDouble("amount"), rs.getString("type"), rs.getString("status"), rs.getInt("creditId")));
			}
			
			dbConn.CloseConnection(conn);
			
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
		}
		
		return aList.size() > 0 ? aList : null;
	}
	
}
