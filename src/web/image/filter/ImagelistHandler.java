package web.image.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagelistHandler extends SessionImageList {

	/* (non-Javadoc)
	 * @see web.image.filter.WebImageFilterHandler#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(getImageList(request) == null){
			System.out.println("New image list");
		request.getSession().setAttribute("imageList", new ArrayList<Image>());
		 try {
				InputStream imageStub = request.getServletContext().getResourceAsStream("/sample.jpg");
				getImageList(request).add(Image.read("sample.jpg",imageStub));
				System.out.println("Add stub");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		List<Image> imageList = getImageList(request);
		request.setAttribute("imagesNumber", imageList.size());
		request.setAttribute("imageList", imageList);
		request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		
	}

}
