import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@WebServlet
(
    name = "HelloAppEngine",
    urlPatterns = {"/hello"}
)

public class HelloAppEngine extends HttpServlet 
{
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) 
  throws IOException 
  {
    response.setContentType("text/plain");
    DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
    
    Entity student1 = new Entity("Student");
    student1.setProperty("Name", "Vani Poudwal");
    student1.setProperty("Address", "Panchsheel Park");
    student1.setProperty("Email", "guddu@niit.com");
    ds.put(student1);
    
    Entity student2 = new Entity("Student");
    student2.setProperty("Name", "Lakku Rangeela");
    student2.setProperty("Address", "Greater Kailash");
    student2.setProperty("Email", "kiitu@niit.com");
    ds.put(student2);
    
    Entity student3 = new Entity("Student");
    student3.setProperty("Name", "Jethalal Gada");
    student3.setProperty("Address", "Punjabi Bagh");
    student3.setProperty("Email", "babita@niit.com");
    ds.put(student3);
    
    Entity student4 = new Entity("Student");
    student4.setProperty("Name", "Vibhuti Narayan");
    student4.setProperty("Address", "Noida");
    student4.setProperty("Email", "bhabhji@niit.com");
    ds.put(student4);
    
    Entity student5 = new Entity("Student");
    student5.setProperty("Name", "Rajjo Katto");
    student5.setProperty("Address", "Faridabad");
    student5.setProperty("Email", "happu@niit.com");
    ds.put(student5); 
    }
}
