package com.niit.ShoppersHub;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.jdo.PersistenceManager;
import com.niit.ShoppersHub.PMF;

@WebServlet("/ShoppersHubServlet")
public class ShoppersHubServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
 
	public ShoppersHubServlet() 
	{
        super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws IOException 
	{
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String cell = request.getParameter("cell");
		Customer c = new Customer();
		c.setName(name);
		c.setAddress(address);
		c.setCellNo(cell);
		PersistenceManager pm = PMF.get().getPersistenceManager();
			
		try 
		{
			pm.makePersistent(c);
		} 
		finally 
		{
			pm.close();
		}
		response.setContentType("text/plain");
		response.getWriter().println("The data is saved.");
	}
}
