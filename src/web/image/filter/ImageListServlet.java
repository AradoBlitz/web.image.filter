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
		if(request.getRequestURL().toString().contains("/imagelist")){
			request.setAttribute("imagesNumber", 3);
			request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		}else if(request.getRequestURL().toString().contains("/image/")){
			InputStream image = getServletContext().getResourceAsStream("/sample.jpg");
			List<byte[]> imageList = new ArrayList<byte[]>();
			ImageFilterApplication imgFApp = new ImageFilterApplication();
			imgFApp.imageList = imageList;
			imgFApp.addImage(image);
			
			response.getOutputStream().write(imageList.get(0));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
