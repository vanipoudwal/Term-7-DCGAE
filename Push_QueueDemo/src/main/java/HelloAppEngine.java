import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;

@WebServlet(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)
public class HelloAppEngine extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
      throws IOException {

    response.setContentType("text/plain");
    try {
 	   String strEmail=request.getParameter("email_field").trim();
 	   String strName=request.getParameter("name_field").trim();
 	   String strPass=request.getParameter("pwd").trim();
 	   if(strEmail==""||strName==""|strPass=="")
 		   throw new Exception("All Fields are required");
 	   		Queue q=QueueFactory.getQueue("subscription-queue");
 	   		q.add(TaskOptions.Builder.withUrl("/task").param("Emailid", strEmail).param("Name", strName).param("Password",strPass).countdownMillis(50000));
 	   		response.sendRedirect("success.html");
    		}
    		catch(Exception e) {
    			e.printStackTrace();
    		}

  }
}