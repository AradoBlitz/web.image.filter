package web.image.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class ImageListServlet
 */
@WebServlet(urlPatterns = {"/image/*","/imagelist","/upload","/download/*","/apply/*","/accept","/filteredimage"})
@MultipartConfig	
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	ImageFilterApplication imgFApp = new ImageFilterApplication();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageListServlet() {
        super();
       
    }

    
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		 try {
				imgFApp.addImage(config.getServletContext().getResourceAsStream("/sample.jpg"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURL());
		System.out.println(request.getContextPath());
		
		String requestPath = request.getRequestURL().toString();
		
		if(requestPath.contains("/imagelist")){
			request.setAttribute("imagesNumber", imgFApp.getImages().size());
			request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		} else if(requestPath.contains("/image/")){
				int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
				List<Image> images = imgFApp.getImages();
				if(index<images.size()) {
					byte[] b = images.get(index).image;
					System.out.println("Image size: " + b.length);
					response.getOutputStream().write(b);
				}
		} else if(requestPath.contains("/download/")){
			
			int index = Integer.parseInt(requestPath.substring(requestPath.lastIndexOf('/')+1)) - 1;
			List<Image> images = imgFApp.getImages();
			if(index<images.size()) {
				response.setContentType("application/octet-stream");
				byte[] b = images.get(index).image;
				response.setContentLength(b.length);
				response.getOutputStream().write(b);
			}
		} else if(requestPath.contains("/apply/")){
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
		} else if(requestPath.contains("/accept")){
			imgFApp.acceptFilteredImage();
			request.setAttribute("imagesNumber", imgFApp.getImages().size());
			request.getServletContext().getRequestDispatcher("/imagelist.jsp").forward(request, response);
		} 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestPath = request.getRequestURL().toString();	
			
		if(requestPath.contains("/upload")){
	        final Part filePart = request.getPart("file");
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        InputStream filecontent = null;
	        filecontent = filePart.getInputStream();
	        int read = 0;
            final byte[] bytes = new byte[1024];

            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
									 
			
			imgFApp.addImage(new ByteArrayInputStream(out.toByteArray()));
			response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
			
		} 
	}

}
