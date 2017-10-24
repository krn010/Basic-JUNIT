package com.karan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Loginvalidatation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       Connection conn=null;
       PreparedStatement pstmt=null;
       String query="select email,password from registration where email=? and password=?";

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String email=request.getParameter("email");
		String pass=request.getParameter("password");
		//out.println(email);
		//out.println(pass);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?user=root&password=root");
			pstmt=conn.prepareStatement(query);
			
			pstmt.setString(1,email);
			pstmt.setString(2,pass);
			
			ResultSet i=pstmt.executeQuery();
			if(i.next())
			{
				RequestDispatcher r=request.getRequestDispatcher("/Welcome.jsp");
				r.forward(request,response);
			}
			else {
				out.println("sorry your email or password is not correct ");
				RequestDispatcher r=request.getRequestDispatcher("/login.jsp");
				r.include(request,response);
				
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(conn!=null)
			{
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
