package com.niit.ShoppersHub;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Customer 
{
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key custId;
	@Persistent
	private String name;
	@Persistent
	private String address;
	@Persistent
	private String cellNo;

	public Key getCustId() 
	{
		return custId;
	}
	
	public void setCustId(Key custid) 
	{
		this.custId = custid;
	}
	
	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}
	
	public String getAddress() 
	{
		return address;
	}
	
	public void setAddress(String address) 
	{
		this.address = address;
	}

	public String getCellNo() 
	{
		return cellNo;
	}
	
	public void setCellNo(String cellno) 
	{
		this.cellNo = cellno;
	}

	public Customer() 
	{
		super();
	}
}
