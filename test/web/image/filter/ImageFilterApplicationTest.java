package web.image.filter;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ImageFilterApplicationTest {
	
//	/static final byte[] SAMPLE_JPG = getImageAsByteArray(ImageFilterApplicationTest.class,"sample.jpg");
	
	ImageFilterApplication containsOneImage = new ImageFilterApplication();

	ImageFilterApplication containsFilteredImage = new ImageFilterApplication();
	
	@Test
	public void getImageList() throws Exception {
		
		assertEquals(0, new ImageFilterApplication().getImages().size());
	}
	
	@Before
	public void initOneImageApplication() throws Exception {
		containsOneImage.addImage(getClass().getClassLoader().getResourceAsStream("sample.jpg"));
		
	}
	
	@Before
	public void initContainsFilteredImage() throws Exception {
		containsFilteredImage.addImage(getClass().getClassLoader().getResourceAsStream("sample.jpg"));
		containsFilteredImage.applyFilter(new FilteredImage(0));
	}
	
	@Test
	public void addOneImage() throws Exception {
	
		
		assertEquals(1, containsOneImage.getImages().size());
	}
	
	@Test
	public void applyFilter() throws Exception {
		
		ByteArrayOutputStream actual = new ByteArrayOutputStream();
	
		containsOneImage.applyFilter(new FilteredImage(0));
		actual.write(containsFilteredImage.getFilteredImage());
		assertArrayEquals(getImageAsByteArray("filtered.jpg"), actual.toByteArray());
	}

	@Test
	public void acceptNullFilteredImage() throws Exception {
		
		containsOneImage.acceptFilteredImage();
		assertArrayEquals(getImageAsByteArray("sample.jpg"), containsFilteredImage.getImages().get(0).image);
	}
	
	@Test
	public void acceptFilteredImage() throws Exception {		
	
		containsFilteredImage.acceptFilteredImage();
		assertArrayEquals(getImageAsByteArray("filtered.jpg"), containsFilteredImage.getImages().get(0).image);
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
}
