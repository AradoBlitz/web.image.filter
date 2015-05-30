package web.image.filter;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public abstract class SessionImageList implements WebImageFilterHandler {

	public List<Image> getImageList(HttpServletRequest request) {
		return (List<Image>) request.getSession().getAttribute("imageList");
	}
}
