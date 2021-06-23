package com.niit.DemoProject;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet
(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)

public class HelloAppEngine extends HttpServlet 
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
	response.getWriter().append("Served at: ").append(request.getContextPath());
    response.setContentType("text/plain");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().println("Hey There! ");
    response.getWriter().println("This Is My First Google App Engine Project! \r\n");
  }
}
