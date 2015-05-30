package web.image.filter;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;

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
		DiffusionFilter filter = new DiffusionFilter();
		BufferedImage arg0 = ImageIO.read(getResource("sample.jpg"));
		BufferedImage arg1 = new BufferedImage(arg0.getWidth(), arg0.getHeight(), arg0.getType());
		filter.filter(arg0 , arg1);
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		ImageIO.write(arg1, "jpg", output);
		
		assertArrayEquals(
				getResourceAsByte(new byte[1246734],"filtered.jpg")
				, output.toByteArray());
	}

	
	
	private byte[] getResourceAsByte(byte[] buff, String string) throws Exception {
		InputStream expected = getClass().getClassLoader().getResourceAsStream(string);
		expected.read(buff);
		return buff;
	}
	
}
