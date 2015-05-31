package web.image.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilteredImageHandler extends SessionImageList implements
		WebImageFilterHandler {

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getOutputStream().write(getFilteredImage(request));

	}

	private byte[] getFilteredImage(HttpServletRequest request) {
		FilteredImage filteredImage = (FilteredImage) request.getSession().getAttribute("filteredImage");
		byte[] image = filteredImage.filtered;
		return image;
	}
}
