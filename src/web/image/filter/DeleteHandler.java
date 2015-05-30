package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteHandler extends SessionImageList {

	
	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
		List<Image> images = getImageList(request);
		if(index<images.size()) { 
			getImageList(request).remove(index);
		}
		response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
		
	}

}
