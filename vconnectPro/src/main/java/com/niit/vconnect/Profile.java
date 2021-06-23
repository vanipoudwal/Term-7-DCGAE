package com.niit.vconnect;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

@Entity
public class Profile {

	@Id
	String email;
	@Index
	String name;
	@Index
	String profession;
	@Index
	long pid;
	String about;
	@Index
	String cell;
	public Profile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Profile(String email, String name, String profession, String about, String cell) {
		super();
		this.email = email;
		this.name = name;
		this.profession = profession;
		this.about = about;
		this.cell = cell;
	}
	public String getEmail() {
		return email;
	}
	public String getName() {
		return name;
	}
	public String getProfession() {
		return profession;
	}
	public String getAbout() {
		return about;
	}
	public String getCell() {
		return cell;
	}

	
	
	
}
