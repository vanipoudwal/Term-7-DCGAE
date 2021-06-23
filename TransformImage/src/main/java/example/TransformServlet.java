package example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.blobstore.BlobKey;
import com.google.appengine.api.blobstore.BlobstoreService;
import com.google.appengine.api.blobstore.BlobstoreServiceFactory;
import com.google.appengine.api.images.Image;
import com.google.appengine.api.images.ImagesService;
import com.google.appengine.api.images.ImagesServiceFactory;
import com.google.appengine.api.images.Transform;

@WebServlet("/transform")
public class TransformServlet extends HttpServlet {
	BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
	BlobKey blobkey = null;
	String imageUrl;
	ImagesService imageService;
	String keyString;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String effect=request.getParameter("effect");
		HttpSession ses=request.getSession(true);
		blobkey = new BlobKey((String)ses.getAttribute("blob-key"));
		imageService = ImagesServiceFactory.getImagesService();
		if(blobkey!=null) {
			Image oldimage=ImagesServiceFactory.makeImageFromBlob(blobkey);
			Transform transform=null;
			if(effect.equals("resize")) {
				transform=ImagesServiceFactory.makeResize(200,200);
			}
			else if(effect.equals("crop")) {
				transform=ImagesServiceFactory.makeCrop(0.1,0.1,.7,.7);
			}
			else if(effect.equals("flipH")) {
				transform=ImagesServiceFactory.makeHorizontalFlip();
			}
			else if(effect.equals("flipV")) {
				transform=ImagesServiceFactory.makeVerticalFlip();
			}
			Image newimage=imageService.applyTransform(transform,oldimage);
			byte[]newImageData=newimage.getImageData();
			response.setContentType("image/jpeg");
			response.getOutputStream().write(newImageData);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
