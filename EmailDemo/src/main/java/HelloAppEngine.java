import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;

@WebServlet
(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)

public class HelloAppEngine extends HttpServlet 
{
  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException 
  {
    response.setContentType("text/html");
    PrintWriter out=response.getWriter();
    String name=request.getParameter("name");
    String email=request.getParameter("email");
    String subject=request.getParameter("subject");
    String message=request.getParameter("message");
    Properties props=new Properties();
    Session session=Session.getDefaultInstance(props, null);
    String msgBody="Hi "+name+"\n"+message+"\n";
    try 
    {
    	Message msg=new MimeMessage(session);
    	msg.setFrom(new InternetAddress("vanipoudwal2000@gmail.com","Admin"));
    	msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email,name));
    	msg.setSubject(subject);
    	msg.setText(message);
    	Transport.send(msg);
    }
    catch(Exception e) 
    {
    	out.println("Some Thing Went Wrong");
    	throw new RuntimeException(e);
    }
    
    out.println("Your Mail is sent, <a href='\\index.html'>Compose another mail</a>");
    MemcacheService memcache=MemcacheServiceFactory.getMemcacheService("user");
    String announceKey="RECENT_KEY";
    String announceText="Last chance to buy";
    memcache.put(announceKey, announceText);
    String value=(String)memcache.get(announceKey);
    out.println("<br>"+value);
  }
}
