package web.image.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
			byte[] buffer = new byte[1024];
			int count = 0;
			InputStream image = getServletContext().getResourceAsStream("/sample.jpg");
			ByteArrayOutputStream outStr = new ByteArrayOutputStream();
			
			while((count = image.read(buffer))>-1){
				outStr.write(buffer, 0, count);
				
			}
			response.getOutputStream().write(outStr.toByteArray());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
