package web.image.filter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class Image {

	public final String name;
	public final byte[] image;

	public Image(String name, byte[] image) {
		this.name = name;
		this.image = image;
	}

	public InputStream toInputStream() {
		
		return  new ByteArrayInputStream(image);
	}

	public String getExtension() {
		
		return "jpg";
	}

	public static Image read(String string, InputStream image) throws IOException {
		byte[] buffer = new byte[1024];
		int count = 0;			
		ByteArrayOutputStream outStr = new ByteArrayOutputStream();			
		while((count = image.read(buffer))>-1){
			outStr.write(buffer, 0, count);				
		}
		return new Image(string, outStr.toByteArray());
	}

}
