package com.niit.FilterSort;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;

@WebServlet
(
    name = "TransactionServlet",
    urlPatterns = {"/TransactionServlet"}
)

public class TransactionServlet extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
    throws IOException 
	{
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		DatastoreService ds=DatastoreServiceFactory.getDatastoreService();
    
		TransactionOptions options=TransactionOptions.Builder.withXG(true);
		Transaction txn=ds.beginTransaction(options);
    
		try 
		{
			Key tomKey;
			Entity tom=new Entity("Person","Tom");
			tomKey=tom.getKey();
			ds.put(tom);
    	
			Entity contact1=new Entity("contactno","tomKey");
			contact1.setProperty("mbno", 88988669);
			Entity contact2=new Entity("contactno","tomKey");
			contact2.setProperty("mbno", 81231230);
			Entity email1=new Entity("emailaddr","tomKey");
			email1.setProperty("email", "Tomhank@hotmail.com");
			Entity email2=new Entity("emailaddr","tomKey");
			email2.setProperty("email", "hanktom@yahoo.com");
    	
			List<Entity> contactList=Arrays.asList(contact1,contact2);
			ds.put(contactList);
			List<Entity> emailList=Arrays.asList(email1,email2);
			ds.put(emailList);
			txn.commit();
		}
		
		finally 
		{
			if(txn.isActive()) 
			{
				txn.rollback();
			}
		}
		out.print("Data Saved!");
	} 
}
