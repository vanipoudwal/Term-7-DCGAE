package com.niit.ObjectifyDemoAssignment;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class Customer 
{
	@Id
	String custID;
	String name;
	String address;
	String phno;
	
	public Customer()
	{
		super();
	}
	
	public Customer(String custID, String name, String address, String phno)
	{
		//super();
		this.custID = custID;
		this.name = name;
		this.address = address;
		this.phno = phno;	
	}
	
	public String getCustID()
	{
		return custID;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getPhno()
	{
		return phno;
	}
}
