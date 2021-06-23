package com.niit.FilterSort;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

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
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
    
		Entity emp1=new Entity("Employee");
		emp1.setProperty("Emp_Id", 101);
    	emp1.setProperty("Title", "HR Manager");
    	emp1.setProperty("Salary",5000);
    	ds.put(emp1);
    
    	Entity emp2=new Entity("Employee");
    	emp2.setProperty("Emp_Id", 102);
    	emp2.setProperty("Title", "Area Manager");
    	emp2.setProperty("Salary",8000);
    	ds.put(emp2);
    
    	Entity emp3=new Entity("Employee");
    	emp3.setProperty("Emp_Id", 103);
    	emp3.setProperty("Title", "Cashier");
    	emp3.setProperty("Salary",4000);
    	ds.put(emp3);
    
    	Entity emp4=new Entity("Employee");
    	emp4.setProperty("Emp_Id", 104);
    	emp4.setProperty("Title", "Executive");
    	emp4.setProperty("Salary",3000);
    	ds.put(emp4);
    
    	Query q=new Query("Employee");
    	Filter propertyfilter=new FilterPredicate("Emp_Id",Query.FilterOperator.GREATER_THAN,102);
    	q.setFilter(propertyfilter);
    	q.addSort("Emp_Id",SortDirection.DESCENDING);
    	q.addSort("Salary");
    
    	PreparedQuery pq=ds.prepare(q);
    	out.println("<p><strong>Query Result:</strong><br/>");
    
    	for(Entity result:pq.asIterable()) 
    	{
    		long Emp_Id=(long) result.getProperty("Emp_Id");
    		String title=(String) result.getProperty("Title");
    		long Salary=(long) result.getProperty("Salary");
    		out.println("Employee ID= "+Emp_Id+"<br>Title= "+title+"<br>Salary= "+Salary+"</p>");
    	}
	}
}
