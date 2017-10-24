package com.karan;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
public class Testcase {

	
    
    @Test
    public void testDoGet() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        HttpServletResponse mockResponse = mock(HttpServletResponse.class);
        ServletOutputStream mockOutput = mock(ServletOutputStream.class);
       
        when(mockRequest.getParameter("name")).thenReturn("satya");
        when(mockRequest.getParameter("email")).thenReturn("s1@gmail.com");
        when(mockRequest.getParameter("mobile")).thenReturn("96151032325");
        when(mockRequest.getParameter("password")).thenReturn("12345");
       
        when(mockRequest.getParameter("address")).thenReturn("bridgelaabz");
     
        
        
     /*   StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        
        when(mockResponse.getWriter()).thenReturn(out);
       */ 
        
       // when(mockResponse.getOutputStream()).thenReturn(mockOutput);

        new InsertData().doGet(mockRequest, mockResponse);
        verify(mockResponse).setContentType("text/html");
        verify(mockResponse).sendRedirect("login.jsp");
       // verify(rd).include(mockRequest, mockResponse);
        
       //verify(mockResponse).getContentType().isEmpty();
    }
}

