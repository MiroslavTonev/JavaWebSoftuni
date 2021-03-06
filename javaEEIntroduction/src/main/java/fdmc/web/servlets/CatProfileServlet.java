package fdmc.web.servlets;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domain.entities.Cat;
import util.HtmlReader;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
	
	private final static String CAT_PROFILE_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\cat-profile.html";
	private final static String CAT_PROFILE_NOT_FOUND_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\non-existend-cat.html";
	
	private final HtmlReader htmlReader; 
	

	@Inject
	public CatProfileServlet(HtmlReader htmlReader) {
		this.htmlReader = htmlReader;
	}


	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
				.get(req.getQueryString().split("=")[1]);
		
		String htmlFileContent = "";
		
		if(cat == null) {
			htmlFileContent = this.htmlReader.readHtmlFile(CAT_PROFILE_NOT_FOUND_HTML_FILE_PATH)
					.replace("{{catName}}", req.getQueryString().split("=")[1]);
		}else {
			htmlFileContent = this.htmlReader.readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
					.replace("{{catName}}", cat.getName())
					.replace("{{catBreed}}", cat.getBreed())
					.replace("{{catColor}}", cat.getColor())
					.replace("{{catAge}}", cat.getAge().toString());
			
		}
		
		if(req.getSession().getAttribute("cats") == null) {
			req.getSession().setAttribute("cats", new LinkedHashMap<>());
		}
		
		((Map<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);				 	
		
		resp.getWriter().println(htmlFileContent);
	}	
}
