package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadHandler extends SessionImageList implements WebImageFilterHandler {

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
		List<Image> images = getImageList(request);
		if(index<images.size()) {
			response.setContentType("application/force-download");
			
			byte[] b = images.get(index).image;
			response.setContentLength(b.length);
			response.setHeader("Content-Transfer-Encoding", "binary");
			response.setHeader("Content-Disposition","attachment; filename=\"" + images.get(index).getName() + "\"");
			response.getOutputStream().write(b);
		}
		
	}

}
