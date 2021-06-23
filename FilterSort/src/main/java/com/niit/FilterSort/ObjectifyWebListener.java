package com.niit.FilterSort;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import com.googlecode.objectify.ObjectifyService;

@WebListener
public class ObjectifyWebListener implements ServletContextListener 
{
	@Override
	public void contextInitialized(ServletContextEvent event) 
	{
		ObjectifyService.init();
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {}
}
