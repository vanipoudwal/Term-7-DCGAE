package com.niit.EndpointsSkeletonDemo;

public class MyBean 
{
	private String msg;
	
	public MyBean() 
	{
		super();
	}
	
	public String getMsg() 
	{
		return msg;
	}

	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public MyBean(String str1, String time) 
	{
		this.msg = "Good "+ time + " " + str1;
	} 
}
