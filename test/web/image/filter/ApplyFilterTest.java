package web.image.filter;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.imageio.ImageIO;

import org.junit.Ignore;
import org.junit.Test;

import com.jhlabs.image.DiffusionFilter;

public class ApplyFilterTest {

	@Ignore
	@Test
	public void listeningFiltersInClasspath() throws Exception {
		
		
	}
	
	@Test
	public void getImageFromClasspath() throws Exception {
		
		assertNotNull("Image in classpath shouldbe provided.",getResource("sample.jpg"));
		assertNotNull("Filtered sample in classpath shouldbe provided.",getResource("filtered.jpg"));
	}

	private URL getResource(String string) {
		// TODO Auto-generated method stub
		return getClass().getClassLoader().getResource(string);
	}
	
	@Test
	public void applyCurlFilterToImage() throws Exception {
		List<Image> imageList = new ArrayList<Image>();
		imageList.add(Image.read("sample.jpg", getResourceAsStream("sample.jpg")));
		FilteredImage imageFilter = new FilteredImage(0);
		imageFilter.applyFilter("DiffusionFilter", imageList);
		
		
		imageFilter.acceptFilteredImage(imageList );
		assertArrayEquals(Image.read("filtered.jpg", getResourceAsStream("filtered.jpg")).image
				, imageList.get(0).image);
	}

	private byte[] getImageAsByteArray(String imageName) throws Exception {
		
		return  getImageAsByteArray(getClass(),imageName);
	}

	private static byte[] getImageAsByteArray(Class class1, String imageName)throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buff = new byte[1246734];
		int count = 0;
		InputStream in = class1.getClassLoader().getResourceAsStream(imageName);
		while((count=in.read(buff))>-1){
			out.write(buff, 0, count);
		}
		return out.toByteArray();
	}
	
	private byte[] getResourceAsByte(byte[] buff, String string) throws Exception {
		InputStream expected = getResourceAsStream(string);
		expected.read(buff);
		return buff;
	}

	private InputStream getResourceAsStream(String string) {
		return getClass().getClassLoader().getResourceAsStream(string);
	}
	
}
