package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApplyHandler implements WebImageFilterHandler {

	private ImageFilterApplication imgFApp;

	public ApplyHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;
		// TODO Auto-generated constructor stub
	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		if(requestPath.contains("/filteredimage")){
			response.getOutputStream().write(imgFApp.getFilteredImage());

		} else if(requestPath.contains("/accept")){
			imgFApp.acceptFilteredImage();
			request.setAttribute("imagesNumber", imgFApp.getImages().size());
			response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
		} else {
			int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1));
			List<Image> images = imgFApp.getImages();
			if(index<=images.size()) {			
				try {
					//System.out.println("Filter name: " + requestPath.split("/")[5]);
					imgFApp.applyFilter(new FilteredImage(index-1));
					request.getServletContext().getRequestDispatcher("/filteredimage.html").forward(request, response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

}
