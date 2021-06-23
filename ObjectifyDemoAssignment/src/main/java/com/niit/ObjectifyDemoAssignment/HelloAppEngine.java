package com.niit.ObjectifyDemoAssignment;

import com.google.appengine.api.utils.SystemProperty;
import java.io.IOException;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
(
		name = "HelloAppEngine", 
		value = "/hello"
)

public class HelloAppEngine extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException 
	{
		Properties properties = System.getProperties();
		response.setContentType("text/plain");
		response.getWriter().println("Hello App Engine !! \r\n" );
  
		Customer c1 = new Customer("C001","Vani Poudwal","Panchsheel Park","1234");
		Customer c2 = new Customer("C002","Guddu Didi","Greater Kailash","5678");
		Customer c3 = new Customer("C003","Jethalal Gada","Punjabi Bagh","9090");
		Customer c4 = new Customer("C004","Daroga Happu","Lajpat Nagar","7531");
		Customer c5 = new Customer("C005","Lakku Rangeela","Sarita Vihar","8642");
		CustomerService.ofy().save().entities(c1,c2,c3,c4,c5).now();
	}
}
