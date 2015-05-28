package web.image.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface WebImageFilterHandler {

	public abstract void handle(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException;

}