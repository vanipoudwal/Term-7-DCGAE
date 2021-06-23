package com.niit.DatastoreWithoutMaven;

import java.io.IOException; 
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)

public class HelloAppEngine extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException 
	{
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("Hello App Engine!\r\n");
		Customer c1 = new Customer("C001","Vani","Panchsheel Park","1234");
		Customer c2 = new Customer("C002","Guddu","Greater Kailash","5678");
		Customer c3 = new Customer("C003","Kittu","Punjabi Bagh","9090");
		CustomerService.ofy().save().entities(c1,c2,c3).now();
	}
}
