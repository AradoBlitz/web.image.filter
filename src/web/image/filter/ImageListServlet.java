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
@WebServlet(urlPatterns = {"/image/*","/imagelist","/upload","/download/*","/apply/*","/accept","/filteredimage","/delete/*"})
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
				InputStream imageStub = config.getServletContext().getResourceAsStream("/sample.jpg");
				imgFApp.addImage(Image.read("sample.jpg",imageStub));
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
		ImagelistHandler imglst = new ImagelistHandler(imgFApp);
		ImageHandler img = new ImageHandler(imgFApp);
		DownloadHandler download = new DownloadHandler(imgFApp);
		DeleteHandler delete = new DeleteHandler(imgFApp);
		ApplyHandler apply = new ApplyHandler(imgFApp);
		
		if(requestPath.contains("/imagelist")){			
			imglst.handle(request,response);
		} else if(requestPath.contains("/image/")){
				
				img.handle(request,response);
		} else if(requestPath.contains("/download/")){
			
			
			download.handler(request,response);
		}  if(requestPath.contains("/delete/")){
			
			
			delete.handle(request,response);
		} else if(requestPath.contains("/apply/")){
			
			apply.handle(request,response);
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
		UploadHandler upload = new UploadHandler(imgFApp);	
		if(requestPath.contains("/upload")){
			
	        
			upload.handle(request,response);
		} 
	}
	
	 

}
