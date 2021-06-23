package com.niit.ObjectifyWithMavenDemo;

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
    response.getWriter().println("Hello App Engine - Standard using "
        + SystemProperty.version.get() + " Java " + properties.get("java.specification.version"));
    Customer c1 = new Customer("C001","Vani","Panchsheel Park","1234");
	Customer c2 = new Customer("C002","Guddu","Greater Kailash","5678");
	Customer c3 = new Customer("C003","Kittu","Punjabi Bagh","9090");
	CustomerService.ofy().save().entities(c1,c2,c3).now();
  }

  public static String getInfo() 
  {
    return "Version: " + System.getProperty("java.version")
          + " OS: " + System.getProperty("os.name")
          + " User: " + System.getProperty("user.name");
  }
}
