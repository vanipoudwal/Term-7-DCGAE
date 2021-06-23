<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.io.IOException"%>
<%@page import="javax.servlet.http.HttpServlet"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>
<%@page import="javax.servlet.http.HttpServletResponse"%>
<%@page
	import="com.google.appengine.api.blobstore.BlobstoreServiceFactory"%>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService"%>
<%@ page import="com.google.appengine.api.blobstore.BlobKey"%>
<%@ page import="com.google.appengine.api.images.ImagesService"%>
<%@ page import="com.google.appengine.api.images.ImagesServiceFactory"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
	BlobKey blobkey = null;
	String imageUrl;
	ImagesService imageService;
	String keyString;
	BlobstoreService bs = BlobstoreServiceFactory.getBlobstoreService();
	blobkey = new BlobKey(request.getParameter("blob-key"));
	session.setAttribute("blob-key", blobkey.getKeyString());
	imageService = ImagesServiceFactory.getImagesService();
	imageUrl = imageService.getServingUrl(blobkey);
	%>
	<div style="width: 80%; align: center">
		<div style="display: block; float: left; margin-right: 10px">
			<img src="<%=imageUrl%>" alt="myimage">
		</div>
		<div style="border: 1px solid black; display: block; float: left;">
			<form action="/transaform" method="get">
				<span>Select Effect</span> <input type="radio" name="effect"
					value="crop">Crop<br /> <input type="radio" name="effect"
					value="flipH">Flip Horizontal<br /> <input type="radio"
					name="effect" value="flipV">Flip vertical <br /> <input
					type="radio" name="effect" value="resize">Resize<br /> <br />
				<input type="submt" value="submit" id="btn">
			</form>
		</div>
	</div>
</body>
</html>