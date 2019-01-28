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

@WebServlet("/cats/all")
public class CatAllServlet extends HttpServlet{
	
	private final static String CAT_ALL_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\cats-all.html";
	private final static String CAT_ALL_MISSING_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\cats-all-missing.html";
	
	
	private final HtmlReader reader;
	
	
	@Inject
	public CatAllServlet(HtmlReader reader) {
		super();
		this.reader = reader;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String htmlFileContent = "";
		StringBuilder catsNames = new StringBuilder();
		
		Map<String, Cat> allCats = new LinkedHashMap<>();		
				
		allCats = (Map<String, Cat>) req.getSession().getAttribute("cats");
		
		if(allCats == null) {
			htmlFileContent = reader.readHtmlFile(CAT_ALL_MISSING_HTML_FILE_PATH);
		}else {
			
			for (Map.Entry<String, Cat> kvp : allCats.entrySet()) {
				catsNames.append(String.format("<a href=/cats/profile?catName=%s>%s</a><br/><br/>", kvp.getKey().toString(), kvp.getKey().toString()));
			}
					
			
			htmlFileContent = reader.readHtmlFile(CAT_ALL_HTML_FILE_PATH)
					.replace("{{catLinks}}", catsNames.toString());
		}
		
		
				
		resp.getWriter().println(htmlFileContent);
		
	}
	
}
