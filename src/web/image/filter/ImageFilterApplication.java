package web.image.filter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ImageFilterApplication {

	
	private List<Image> imgObjList = new ArrayList<>();
	private FilteredImage filteredImage;

	public void addImage(InputStream image) throws IOException {
		

		imgObjList.add(Image.read("input.jpg",image));

	}

	public List<Image> getImages() {
		// TODO Auto-generated method stub
		return imgObjList;
	}	

	public byte[] getFilteredImage() {
		return filteredImage.filtered;
	}

	public void applyFilter(FilteredImage filteredImage2) throws IOException {
		filteredImage = filteredImage2;
		filteredImage.applyFilter(imgObjList);
	}

	public void acceptFilteredImage() {
		if(filteredImage == null)return;
		filteredImage.acceptFilteredImage(imgObjList);		
	}

}
