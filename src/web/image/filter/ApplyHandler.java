package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyHandler extends SessionImageList {

	

	public ApplyHandler() {
	
	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
			int index = Integer.parseInt(request.getParameter("image"));
			List<Image> images = getImageList(request);
			if(index<=images.size()) {			
				try {
					
					applyFilterToImage(request, index, getImageList(request));
 					request.getServletContext().getRequestDispatcher("/filteredimage.html").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		
	}

	private void applyFilterToImage(HttpServletRequest request, int index,
			List<Image> imageList) throws IOException {
		String filterName = request.getParameter("filter");
		FilteredImage filteredImage = new FilteredImage(index-1);
		filteredImage.applyFilter(filterName,imageList);
		request.getSession().setAttribute("filteredImage", filteredImage);
	}

	private FilteredImage acceptAppliedFilter(HttpServletRequest request)
			throws IOException {
		FilteredImage filteredImage = (FilteredImage) request.getSession().getAttribute("filteredImage");
		return filteredImage;//filteredImage.applyFilter(getImageList(request));
	}

	private byte[] getFilteredImage(HttpServletRequest request) {
		FilteredImage filteredImage = (FilteredImage) request.getSession().getAttribute("filteredImage");
		byte[] image = filteredImage.filtered;
		return image;
	}

}
