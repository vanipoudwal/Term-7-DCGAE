package com.niit.MemcacheDemo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@WebServlet(name = "HelloAppEngine", value = "/hello")
public class HelloAppEngine extends HttpServlet 
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response)
  throws IOException 
  {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    MemcacheService memcache=MemcacheServiceFactory.getMemcacheService("user");
    String announceKey="RECENT_KEY";
    String announceText="Last chance to buy";
    memcache.put(announceKey, announceText);
    String value=(String)memcache.get(announceKey);
    out.println("<br>"+value);
  }
}
