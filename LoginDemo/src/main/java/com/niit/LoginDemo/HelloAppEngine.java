package com.niit.LoginDemo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.api.utils.SystemProperty;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws IOException 
	{
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		UserService us=UserServiceFactory.getUserService(); 
	 
		User user=us.getCurrentUser();
		if(user!=null)
		{   
			out.println("<br>");
			out.println("<h2>Welcome </h2>"+user.getNickname());
			out.println("<br>"+" Logged in "+us.isUserLoggedIn()+"<br>"+" Admin: "+us.isUserAdmin());
			out.println("<br>Domain: "+user.getAuthDomain()+"<br>"+" Email "+user.getEmail()+"<br>"+" User ID: "+user.getUserId());
			out.println("<br><a href='"+us.createLogoutURL("/hello")+"'>Logout</a>");
			out.println("<br>");
		}
		else
		{
			out.println("<br>");
			out.println("<h3>You are not logged in</h3>");
			out.println("<a href='"+us.createLoginURL(request.getRequestURL().toString())+"'>Login</a>");
			out.println("<br>");
		}
	}
}
