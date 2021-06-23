package com.niit.vconnect;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class UserCounter {
	public UserCounter() {
		super();
		// TODO Auto-generated constructor stub
	}

	long counter;
	@Id
	String counterid;

	public UserCounter(long counter, String counterid) {
		super();
		this.counter = counter;
		this.counterid = "1";

	}

	public long getCounter() {
		return counter;
	}

	public void setCounter(long counter) {
		this.counter = counter;
	}

}
