package com.niit.FilterSort;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.datastore.Query.SortDirection;

@WebServlet
(
    name = "AncestorQueryServlet",
    urlPatterns = {"/AncestorServlet"}
)

public class AncestorQueryServlet extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
    
		Entity tom=new Entity("Person","Tom");
		Key tomKey=tom.getKey();
		ds.put(tom);
    
		Entity contact1=new Entity("contactno",tomKey);
		contact1.setProperty("mbno", 1912312310);
		Entity contact2=new Entity("contactno",tomKey);
		contact2.setProperty("mbno", 1111111310);
    
		Entity email1=new Entity("emailaddr",tomKey);
		email1.setProperty("email", "megha@gmail.com");
		Entity email2=new Entity("emailaddr",tomKey);
		email2.setProperty("email", "rishabhraj@gmail.com");
    
		List<Entity> contactList=Arrays.asList(contact1,contact2);
		ds.put(contactList);
		List<Entity> emailList=Arrays.asList(email1,email2);
		ds.put(emailList);
    
		Query contactQ=new Query("contactno").setAncestor(tomKey);
		Query emailQ=new Query("emailaddr").setAncestor(tomKey);
    
		List<Entity> results=ds.prepare(contactQ).asList(FetchOptions.Builder.withDefaults());
		List<Entity> results1=ds.prepare(emailQ).asList(FetchOptions.Builder.withDefaults());
    
		String str=tomKey.getName();
		out.println("Entity Name: "+str);
		out.println("<br>");
		out.println("Contact numbers are: ");
    
		Iterator i=results.iterator();
		while(i.hasNext())
		{
			Entity element=(Entity) i.next();
    	
			if(i.hasNext())
				out.print(element.getProperty("mbno")+", ");
			else
				out.print(element.getProperty("mbno"));
		}
		out.println("<br>");
		out.print("Email Ids are: ");
    
		Iterator it=results1.iterator();
		while(it.hasNext())
		{
			Entity element=(Entity) it.next();
    	
			if(it.hasNext())
				out.print(element.getProperty("email")+", ");
			else
				out.print(element.getProperty("email"));
		}
	}
}