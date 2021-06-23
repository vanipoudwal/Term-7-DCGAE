package com.niit.AncestorQuery;

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
import com.google.appengine.api.datastore.Query;

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
		Entity tom=new Entity("Person","Tom");
		tom.setProperty("pname", "vani");
		Key tomkey=tom.getKey();
		ds.put(tom);

		Entity contact1=new Entity("contactno",tomkey);
		contact1.setProperty("mbno", 1234567890);
		
		Entity contact2=new Entity("contactno",tomkey);
		contact2.setProperty("mbno", 90087634);
		
		Entity email1=new Entity("emailaddr",tomkey);
		email1.setProperty("email", "s1@yahoo.com");
		
		Entity email2=new Entity("emailaddr",tomkey);
		email2.setProperty("email", "s2@yahoo.com");
		
		List<Entity>contactList=Arrays.asList(contact1,contact2);
		ds.put(contactList);
		
		List<Entity>emailList=Arrays.asList(email1,email2);
		ds.put(emailList);
    
		Query contactQuery=new Query("contactno").setAncestor(tomkey);
		Query emailQuery=new Query("emailaddr").setAncestor(tomkey);
		List<Entity>results=ds.prepare(contactQuery).asList(FetchOptions.Builder.withDefaults());
		List<Entity>results1=ds.prepare(emailQuery).asList(FetchOptions.Builder.withDefaults());
    
		String str=tomkey.getName();
		out.println("Entity Name: "+str);
		out.println("<br>");
		out.println("Contact No are: ");
    
		Iterator it=results.iterator();
		while(it.hasNext()) 
		{
			Entity element=(Entity)it.next();
			if(it.hasNext()) 
			{
				out.print((Long)element.getProperty("mbno")+", ");
			}
    		else
    			out.print((Long)element.getProperty("mbno"));
    	}
		out.println("<br>");
		
		Iterator it1=results1.iterator();
		while(it1.hasNext()) 
		{
			Entity element=(Entity)it1.next();
			if(it1.hasNext()) 
			{
				out.print((String)element.getProperty("email")+", ");
			}
    		else
    			out.print((String)element.getProperty("email"));
    	}   
    }
}
