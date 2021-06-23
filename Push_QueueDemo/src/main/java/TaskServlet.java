

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.jdo.PersistenceManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/task")
public class TaskServlet extends HttpServlet {
	
	public void saveData(String email,String name,String pwd) {
		
		Subscriber c=new Subscriber();
		c.setEmail(email);
		c.setName(name);
		c.setPwd(pwd);
		PersistenceManager pm=PMF.get().getPersistenceManager();
		try {
			pm.makePersistent(c);
		}finally {
			pm.close();
		}
		
	}

	public void sendEmail(String email,String name) throws UnsupportedEncodingException, MessagingException {
		Properties props=new Properties();
		Session session=Session.getDefaultInstance(props,null);
		String msgBody="Thank you";
		Message msg=new MimeMessage(session);
		msg.setFrom(new InternetAddress("support@app-id.appspot.com", "The News"));
		msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email,name));
		msg.setSubject("Confirmation");
		msg.setText(msgBody);
		Transport.send(msg);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String email=request.getParameter("emailid");
			String name=request.getParameter("name");
			String pwd=request.getParameter("pwd");
			saveData(email,name,pwd);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
