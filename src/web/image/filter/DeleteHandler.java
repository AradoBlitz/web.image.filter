package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteHandler implements WebImageFilterHandler {

	private ImageFilterApplication imgFApp;

	public DeleteHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;
		// TODO Auto-generated constructor stub
	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
		List<Image> images = imgFApp.getImages();
		if(index<images.size()) { 
			imgFApp.getImages().remove(index);
		}
		response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
		
	}

}
