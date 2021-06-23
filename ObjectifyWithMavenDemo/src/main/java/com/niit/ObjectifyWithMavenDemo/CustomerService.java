package com.niit.ObjectifyWithMavenDemo;

import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.ObjectifyService;

public class CustomerService 
{	
	static 
	{
		factory().register(Customer.class);
	}
	
	public static Objectify ofy() 
	{
		return ObjectifyService.ofy();
	}
	
	public static ObjectifyFactory factory() 
	{
		return ObjectifyService.factory();
	}
}
