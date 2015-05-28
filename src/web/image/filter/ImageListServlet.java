package web.image.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	private Map<String,WebImageFilterHandler> handlerMap = new HashMap<>();
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
		 	handlerMap.put("upload", new UploadHandler(imgFApp));			
			handlerMap.put("imagelist", new ImagelistHandler(imgFApp));
			handlerMap.put("image", new ImageHandler(imgFApp));
			handlerMap.put("download",new DownloadHandler(imgFApp));
			handlerMap.put("delete",new DeleteHandler(imgFApp));
			handlerMap.put("apply",new ApplyHandler(imgFApp));			
			
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		String command = request.getRequestURI().split("/")[2];
		System.out.println("Command " + command);
		
		
		handlerMap.get(command).handle(request,response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRequestURI());
		String command = request.getRequestURI().split("/")[2];
		System.out.println("Command " + command);
		
		handlerMap.get("upload").handle(request,response);
		 
	}
	
	 

}
