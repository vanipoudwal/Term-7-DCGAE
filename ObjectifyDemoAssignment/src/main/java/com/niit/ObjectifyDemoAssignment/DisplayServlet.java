package com.niit.ObjectifyDemoAssignment;

import java.util.List;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/show")
public class DisplayServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		List<Customer> list = CustomerService.ofy().load().type(Customer.class).list();
		
		for(Customer customer : list)
		{
			out.println("CustID: " + customer.getCustID());
			out.println("Name: " + customer.getName());
			out.println("Address: " + customer.getAddress());
			out.println("Phone No: " + customer.getPhno());
		}
	}
}
