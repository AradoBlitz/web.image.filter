package web.image.filter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import com.jhlabs.image.DiffusionFilter;
import com.sun.xml.internal.ws.util.ByteArrayBuffer;

public class ImageFilterApplication {

	private List<byte[]> imageList = new ArrayList<byte[]>();
	private byte[] filtered;
	private int filteredIndex;

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
		return imageList;
	}

	public void applyFilter(int i, String string, ByteArrayOutputStream actual) throws Exception {
		
		int index = i-1;
		BufferedImage source = ImageIO.read(new ByteArrayInputStream(imageList.get(index)));
		BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		
		DiffusionFilter filter = new DiffusionFilter();
		filter.filter(source, result);
		
		ImageIO.write(result, "jpg", actual);
		filtered = actual.toByteArray();
		this.filteredIndex = index;
	}

	public void acceptFilteredImage() {
		imageList.set(filteredIndex, filtered);
		
	}

}
