package in.mh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.mh.dbconnection.DatabaseConnection;
import in.mh.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/loginForm")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out = resp.getWriter();
		resp.setContentType("text/html");
		
		String myEmail = req.getParameter("email1");
		String myPassword = req.getParameter("pass1");
		
		try
		{
			Connection con = DatabaseConnection.getConnection();
			
			String sql_query = "SELECT * FROM register where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(sql_query);
			ps.setString(1, myEmail);
			ps.setString(2, myPassword);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
			{
				User user = new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setGender(rs.getString("gender"));
				
				HttpSession session = req.getSession();
				session.setAttribute("session_user", user);
				
				RequestDispatcher rd = req.getRequestDispatcher("/profile.jsp");
				rd.forward(req, resp);
				
			}
			else
			{
				out.println("<h3 style='color:red'>Email or Password didn't matched</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
