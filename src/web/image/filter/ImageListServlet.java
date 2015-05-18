package web.image.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Buffer;

/**
 * Servlet implementation class ImageListServlet
 */
@WebServlet(urlPatterns = {"/image/*","/imagelist"})
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		ImageFilterApplication imgFApp = new ImageFilterApplication();
		imgFApp.addImage(getServletContext().getResourceAsStream("/sample.jpg"));
		String requestPath = request.getRequestURL().toString();
		
		if(requestPath.contains("/imagelist")){
			request.setAttribute("imagesNumber", imgFApp.getImages().size());
			request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		} else {
			
			if(requestPath.contains("/image/")){
				int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
				List<byte[]> images = imgFApp.getImages();
				if(index<images.size())
					response.getOutputStream().write(images.get(index));
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
