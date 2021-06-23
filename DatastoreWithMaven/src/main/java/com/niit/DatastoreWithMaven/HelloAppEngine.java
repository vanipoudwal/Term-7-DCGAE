package com.niit.DatastoreWithMaven;

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
    response.setContentType("text/html");
    String cid=request.getParameter("txt1");
    String cname=request.getParameter("txt2");
    String add=request.getParameter("txt3");
    String ph=request.getParameter("txt4");
    Customer c=new Customer(cid,cname,add,ph);
    CustomerService.ofy().save().entity(c).now();
  }
}
