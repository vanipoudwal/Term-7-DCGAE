package com.niit.FilterSort;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

@WebServlet("/KindServlet")
public class KindServlet extends HttpServlet 
{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	    response.setContentType("text/html");
	    PrintWriter out=response.getWriter();    
	    DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
	    
	    Entity emp1=new Entity("Emp_Id");
	    emp1.setProperty("Emp_Id", 101);
	    emp1.setProperty("Title", "SR Manager");
	    emp1.setProperty("Salary",5000);
	    ds.put(emp1);
	    
	    Entity emp2=new Entity("Emp_Id");
	    emp2.setProperty("Emp_Id", 102);
	    emp2.setProperty("Title", "Area Manager");
	    emp2.setProperty("Salary",8000);
	    ds.put(emp2);
	    
	    Entity emp3=new Entity("Emp_Id");
	    emp3.setProperty("Emp_Id", 103);
	    emp3.setProperty("Title", "Cashier");
	    emp3.setProperty("Salary",4000);
	    ds.put(emp3);
	    
	    Entity emp4=new Entity("Emp_Id");
	    emp4.setProperty("Emp_Id", 104);
	    emp4.setProperty("Title", "Executive");
	    emp4.setProperty("Salary",3000);
	    ds.put(emp4);
	
	    Key myKey = KeyFactory.createKey("Emp", 101);
	    Key key = KeyFactory.createKey("Emp", 1021);
	    Filter keyFilter =  new FilterPredicate(Entity.KEY_RESERVED_PROPERTY, FilterOperator.GREATER_THAN, key);
	    
	    Query q = new Query();
	    q.setFilter(keyFilter);
    
	    PreparedQuery pq = ds.prepare(q);
	    out.println("<p><strong>Query Result: </strong><br/>");
    
	    for(Entity result : pq.asIterable()) 
	    {
	    	long Emp_Id = (long) result.getProperty("Emp_Id");
	    	String title  = (String) result.getProperty("Title");
	    	long Salary = (long) result.getProperty("Salary");
	    	out.println("Emp ID = " + Emp_Id+"<br>Title = "+title+"<br>Salary = "+Salary+"</p>");
	    }
    
	    Query q1 = new Query("Emp").setKeysOnly();
	    q1.setFilter(keyFilter);
	    PreparedQuery pq1 = ds.prepare(q1);
	    out.println("<p><strong>key only Result: </strong><br />");
	    out.println(pq1.countEntities());
	    out.println(pq1.toString());
    }
}
