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
import com.google.appengine.api.datastore.Query.CompositeFilterOperator;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

@WebServlet
(
    name = "CompositeFilter",
    urlPatterns = {"/CompositeFilter"}
)

public class CompositeFilter extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		DatastoreService Ds=DatastoreServiceFactory.getDatastoreService();
    
		Entity e1=new Entity("Book");
		e1.setProperty("BookId", 100);
		e1.setProperty("Title", "Java Beginners");
    	e1.setProperty("Author", "James");
    	Ds.put(e1);
    
    	Entity e2=new Entity("Book");
    	e2.setProperty("BookId", 101);
    	e2.setProperty("Title", "Let us C");
    	e2.setProperty("Author", "Peter");
    	Ds.put(e2);
    
    	Entity e3=new Entity("Book");
    	e3.setProperty("BookId", 103);
    	e3.setProperty("Title", "Advance Java");
    	e3.setProperty("Author", "Ad Frank");
    	Ds.put(e3);
    
    	Entity e4=new Entity("Book");
    	e4.setProperty("BookId", 104);
    	e4.setProperty("Title", "RWD");
    	e4.setProperty("Author", "James");
    	Ds.put(e4);
    
    	Entity e5=new Entity("Book");
    	e5.setProperty("BookId", 105);
    	e5.setProperty("Title", "SQL Server");
    	e5.setProperty("Author", "James");
    	Ds.put(e5);
    
    	Query q=new Query("Book");
    	Filter propertyfilter=new FilterPredicate("BookId",Query.FilterOperator.GREATER_THAN,101);
    	Filter propertyfilter1=new FilterPredicate("Author",Query.FilterOperator.EQUAL,"James");
    	Filter filter=CompositeFilterOperator.and(propertyfilter,propertyfilter1);
    	q.setFilter(filter);
    	q.addSort("BookId",SortDirection.ASCENDING);
    
    	PreparedQuery pqu=Ds.prepare(q);
    	out.println("<strong>Query Result:</strong><br/>");
    	out.println("<table style='border:2px solid black' cellspacing=20 width=25%><tr><th>BookId</th><th>Title</th><th>Author</th></tr>");
    	
    	for(Entity res:pqu.asIterable()) 
    	{
    		long Id=(long) res.getProperty("BookId");
    		String title=(String) res.getProperty("Title");
    		String author=(String) res.getProperty("Author");
    		out.println("<tr><td>"+Id+"</td><td>"+title+"</td><td>"+author+"</td></tr>");
    	}
    	out.println("</table>");
	}
}
