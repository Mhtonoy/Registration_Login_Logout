package in.mh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import in.mh.dbconnection.DatabaseConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/registerForm")
public class Registration extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String myName = req.getParameter("name1");
		String myEmail = req.getParameter("email1");
		String myPassword = req.getParameter("pass1");
		String myGender = req.getParameter("gender1");
		
		
		try 
		{
			Connection con = DatabaseConnection.getConnection();
			
			String sqlQuery = "insert into register values(?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sqlQuery);
			ps.setString(1, myName);
			ps.setString(2, myEmail);
			ps.setString(3, myPassword);
			ps.setString(4, myGender);
			
			int count = ps.executeUpdate();
			
			if(count > 0)
			{
				out.println("<h3 style='color:green'>Registration Successful</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}
			else
			{
				out.println("<h3 style='color:green'>Registration unsuccessful</h3>");
				
				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
			}
			
		} catch (SQLException e) 
		{
			out.println("<h3 style='color:red'>" + e.getMessage() + "</h3>");
			
			RequestDispatcher rd = req.getRequestDispatcher("/register.html");
			rd.include(req, resp);
		}
		
	}
}
