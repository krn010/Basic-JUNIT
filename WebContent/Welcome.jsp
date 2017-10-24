<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.SQLException" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String email=request.getParameter("email");%>

<%
Connection conn=null;
PreparedStatement pstmt=null;
try{


String query="select * from registration where email=?";
Class.forName("com.mysql.jdbc.Driver");
conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Test?user=root&password=root");
pstmt=conn.prepareStatement(query);
pstmt.setString(1, email);
ResultSet i=pstmt.executeQuery();
%>

<fieldset>
<legend>Personal Details</legend>
<form>
<table>
<%
while(i.next())
{
%>	<tr><td>Name 	: <% out.println(i.getString("name")); %></td></tr>
	<tr><td>Email 	: <% out.println(i.getString("email")); %></td></tr>
	<tr><td>Mobile 	:<% out.println(i.getString("mobile")); %></td></tr>
	<tr><td>Address	: <% out.println(i.getString("address")); %></td></tr>
<% 	
	
	
	
	
	
	
}
%>
</table>
</form>
</fieldset>
<%

}
catch (ClassNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
catch (SQLException e) {
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
}

%>
</body>
</html>