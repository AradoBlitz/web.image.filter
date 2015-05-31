package web.image.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AcceptFilteredImageHandler extends SessionImageList implements
		WebImageFilterHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		acceptAppliedFilter(request).acceptFilteredImage(getImageList(request));
		
		request.setAttribute("imagesNumber", getImageList(request).size());
		response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
	}

	private FilteredImage acceptAppliedFilter(HttpServletRequest request)
			throws IOException {
		FilteredImage filteredImage = (FilteredImage) request.getSession().getAttribute("filteredImage");
		return filteredImage;//filteredImage.applyFilter(getImageList(request));
	}
}
