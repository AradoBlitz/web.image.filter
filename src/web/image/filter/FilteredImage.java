package web.image.filter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import com.jhlabs.image.DiffusionFilter;

public class FilteredImage {

	public final int index;
	public byte[] filtered;
	private Image filteredObj;

	public FilteredImage(int index) {
		this.index = index;
		// TODO Auto-generated constructor stub
	}

	public void applyFilter(List<Image> imgObjList) throws IOException {
		BufferedImage source = ImageIO.read(imgObjList.get(index).toInputStream());
		BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		
		DiffusionFilter filter = new DiffusionFilter();
		filter.filter(source, result);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(result, imgObjList.get(index).getExtension(), baos);
		filteredObj = new Image(imgObjList.get(index).name, baos.toByteArray());
		filtered = baos.toByteArray();
		
	}

	public void acceptFilteredImage(List<Image> imageList) {

		imageList.set(index, filteredObj);
		
		
	}

}
