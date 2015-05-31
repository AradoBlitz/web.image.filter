package web.image.filter;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import com.jhlabs.image.DiffusionFilter;
import com.jhlabs.image.WholeImageFilter;

public class FilteredImage {

	public final static Map<String,WholeImageFilter> filterMap = new HashMap<>();
	static {
		filterMap.put("DiffusionFilter", new DiffusionFilter());
	}
	public final int index;
	public byte[] filtered;
	private Image filteredObj;

	public FilteredImage(int index) {
		this.index = index;
		// TODO Auto-generated constructor stub
	}

	public void applyFilter(List<Image> imgObjList) throws IOException {
		String filterName = "DiffusionFilter";
		
		applyFilter(filterName, imgObjList);
		
	}

	public void applyFilter(String filterName, List<Image> imgObjList)
			throws IOException {
		BufferedImage source = ImageIO.read(imgObjList.get(index).toInputStream());
		BufferedImage result = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
		
		filterMap.get(filterName).filter(source, result);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(result, imgObjList.get(index).getExtension(), baos);
		filteredObj = imgObjList.get(index).updateByteBody(baos.toByteArray());
		filtered = baos.toByteArray();
	}

	public void acceptFilteredImage(List<Image> imageList) {

		imageList.set(index, filteredObj);
		
		
	}

}
