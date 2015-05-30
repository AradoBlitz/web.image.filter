package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageHandler extends SessionImageList implements WebImageFilterHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
		List<Image> images = getImageList(request);
		if(index<images.size()) {
			byte[] b = images.get(index).image;
			System.out.println("Image size: " + b.length);
			response.getOutputStream().write(b);
		}		
	}
}
