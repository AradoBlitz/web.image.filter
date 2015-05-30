package web.image.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageListServlet
 */
@WebServlet(urlPatterns = {"/image/*","/imagelist","/upload","/download/*","/apply/*","/accept","/filteredimage","/delete/*"})
@MultipartConfig	
public class ImageListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
		
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
		
		 	handlerMap.put("upload", new UploadHandler());			
			handlerMap.put("imagelist", new ImagelistHandler());
			handlerMap.put("image", new ImageHandler());
			handlerMap.put("download",new DownloadHandler());
			handlerMap.put("delete",new DeleteHandler());
			handlerMap.put("apply",new ApplyHandler());			
			
	}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println(request.getRequestURI());
		String command = request.getRequestURI().split("/")[2];
		System.out.println("Command " + command);
		System.out.println("Current image list: " + request.getSession().getAttribute("imageList"));
		
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
