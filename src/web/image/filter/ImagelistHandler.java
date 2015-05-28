package web.image.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagelistHandler implements WebImageFilterHandler {

	private ImageFilterApplication imgFApp;

	public ImagelistHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;

	}

	/* (non-Javadoc)
	 * @see web.image.filter.WebImageFilterHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("imagesNumber", imgFApp.getImages().size());
		request.setAttribute("imageList", imgFApp.getImages());
		request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		
	}

}
