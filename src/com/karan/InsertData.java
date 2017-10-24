package com.karan;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdk.nashorn.internal.ir.RuntimeNode.Request;


public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	int password,i;
	String name,email,address,mobile;
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	String query="insert into registration(name,email,mobile,password,address) values(?,?,?,?,?) ";
    
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		try {
			PrintWriter out=response.getWriter();
			response.setContentType("text/html");
			
			name=request.getParameter("name");
			
			email=request.getParameter("email");
			
			mobile=request.getParameter("mobile");
			if(mobile.length()>10)
			{
				out.println("mobile number should be 10 digit");
				RequestDispatcher rd=request.getRequestDispatcher("/Registration.jsp");
				rd.include(request,response);
				return;
			}
	
			password=Integer.parseInt(request.getParameter("password"));
			
			address=request.getParameter("address");
		
			

			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?user=root&password=root");
			pstmt=conn.prepareStatement(query);
			
			
			pstmt.setString(1,name);
			pstmt.setString(2,email);
			pstmt.setString(3,mobile);
			pstmt.setInt(4,password);
			pstmt.setString(5,address);
			
			i=pstmt.executeUpdate();
			
			if(i!=0)
			{ 
				
				response.sendRedirect("login.jsp");	
				
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
