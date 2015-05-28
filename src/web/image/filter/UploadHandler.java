package web.image.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

public class UploadHandler implements WebImageFilterHandler {

	private ImageFilterApplication imgFApp;

	public UploadHandler(ImageFilterApplication imgFApp) {
		this.imgFApp = imgFApp;
		// TODO Auto-generated constructor stub
	}

	public void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		final Part filePart = request.getPart("file");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        InputStream filecontent = null;
        filecontent = filePart.getInputStream();
        int read = 0;
        final byte[] bytes = new byte[1024];

        while ((read = filecontent.read(bytes)) != -1) {
            out.write(bytes, 0, read);
        }
								 
		
		String fileName = getFileName(filePart);
		System.out.println("Uploaded file name: " + fileName);
		imgFApp.addImage(Image.read(fileName,new ByteArrayInputStream(out.toByteArray())));
		response.sendRedirect("http://localhost:8080" + request.getContextPath() + "/imagelist");
		
	}

	private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }
}
