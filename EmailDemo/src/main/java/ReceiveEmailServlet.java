
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReceiveEmailServlet")
public class ReceiveEmailServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final Logger log=Logger.getLogger(ReceiveEmailServlet.class.getName());
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");
	    PrintWriter out=response.getWriter();
	    Properties props=new Properties();
	    Session session=Session.getDefaultInstance(props, null);
	   
	    try 
	    {
	    	MimeMessage msg=new MimeMessage(session,request.getInputStream());
	    	Object content=msg.getContent();
	    	if(content instanceof String)
	    	{
	    		log.warning("Received "+msg.getContentType()+" "+content);
	    	}
	    	else if (content instanceof Multipart) 
	    	{
	    		log.info("Received Multipart "+((Multipart)content).getCount());
	    		for(int i=0;i<((Multipart)content).getCount();i++) 
	    		{
	    			Part part=((Multipart)content).getBodyPart(i);
	    			String partText=(String)part.getContent();
	    			log.warning("Part ["+i+"]"+partText);
	    		}
	    	}
	    }
	    catch(Exception e) 
	    {
	    	out.println("Some Thing Went Wrong");
	    	log.warning("Exception");
	    }
	  }
	}
