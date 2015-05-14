package web.image.filter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ImageFilterApplication {

	public List<byte[]> imageList;

	public void addImage(InputStream image) throws IOException {
		byte[] buffer = new byte[1024];
		int count = 0;			
		ByteArrayOutputStream outStr = new ByteArrayOutputStream();			
		while((count = image.read(buffer))>-1){
			outStr.write(buffer, 0, count);				
		}

		imageList.add(outStr.toByteArray());

	}

	public List<byte[]> getImages() {
		// TODO Auto-generated method stub
		return null;
	}

}
