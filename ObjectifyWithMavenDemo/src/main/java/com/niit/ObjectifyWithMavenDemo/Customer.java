package com.niit.ObjectifyWithMavenDemo;

import com.googlecode.objectify.annotation.Entity; 
import com.googlecode.objectify.annotation.Id;

@Entity
public class Customer 
{	
	@Id
	String custid;
	String name,address,contactno;
	
	public Customer() 
	{
		super();
	}
	
	public Customer(String custid, String name, String address, String contactno) 
	{
		super();
		this.custid = custid;
		this.name = name;
		this.address = address;
		this.contactno = contactno;
	}
	
	public String getCustid() 
	{
		return custid;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public String getContactno() 
	{
		return contactno;
	}
}
