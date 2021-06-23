package com.niit.vconnect;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Profile 
{
	@Id
	String name;
	@Index
	String email;
	@Index
	String profession;
	@Index
	long pid;
	String about;
	@Index
	String cell;
	
	public Profile() 
	{
		super();
	}
	
	public Profile(String name, String email, String profession, String about, String cell) 
	{
		super();
		this.name = name;
		this.email = email;
		this.profession = profession;
		this.about = about;
		this.cell = cell;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public String getEmail() 
	{
		return email;
	}
	
	public String getProfession() 
	{
		return profession;
	}
	
	public String getAbout() 
	{
		return about;
	}
	
	public String getCell() 
	{
		return cell;
	}
}
