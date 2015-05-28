package web.image.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadHandler {

	private ImageFilterApplication imgFApp;

	public DownloadHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;
		// TODO Auto-generated constructor stub
	}

	public void handler(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String requestPath = request.getRequestURL().toString();
		int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
		List<Image> images = imgFApp.getImages();
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
