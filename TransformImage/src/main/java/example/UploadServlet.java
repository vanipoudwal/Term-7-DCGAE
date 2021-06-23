package example;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobKey;
@WebServlet("/Upload")
public class UploadServlet extends HttpServlet {
	BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession ses=request.getSession(true);
		Map<String,List<BlobKey>>blobs=bs.getUploads(request);
		List<BlobKey>blobKeys=blobs.get("coins.jpg");
		if(blobKeys==null ||blobKeys.isEmpty()) {
			response.sendRedirect("/");
		}
		else {
			response.sendRedirect("DisplayImage.jsp?blob-key="+blobKeys.get(0).getKeyString());
		}
		
	}

}
