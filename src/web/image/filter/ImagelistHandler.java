package web.image.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagelistHandler {

	private ImageFilterApplication imgFApp;

	public ImagelistHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;

	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("imagesNumber", imgFApp.getImages().size());
		request.setAttribute("imageList", imgFApp.getImages());
		request.setAttribute("image", imgFApp.getImages().get(0));
		request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		
	}

}