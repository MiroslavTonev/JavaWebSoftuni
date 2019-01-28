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

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet{
	
	private final String CREATE_CATS_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\cat-create.html";
	
	private final HtmlReader reader;
	
	@Inject
	public CatCreateServlet(HtmlReader reader) {		
		this.reader = reader;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.getWriter().println(this.reader.readHtmlFile(CREATE_CATS_HTML_FILE_PATH));
		
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Cat cat = new Cat();
		cat.setName(req.getParameter("name"));
		cat.setBreed(req.getParameter("breed"));
		cat.setColor(req.getParameter("color"));
		cat.setAge(Integer.parseInt(req.getParameter("age")));
		
		if(req.getSession().getAttribute("cats") == null) {
			req.getSession().setAttribute("cats", new LinkedHashMap<>());
		}
		
		((Map<String, Cat>)req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
		
		resp.sendRedirect(String.format("/cats/profile?catName=%s", cat.getName()));
	}
	
	
}
