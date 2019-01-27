package fdmc.web.servlets;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.HtmlReader;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
	
	private final static String INDEX_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\FDMC\\src\\main\\resources\\views\\index.html"; 
	
	private final HtmlReader htmlReader;
	
	@Inject
	public IndexServlet(HtmlReader htmlReader) {
		this.htmlReader = htmlReader;
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();        
       
        writer.println(htmlReader.readHtmlFile(INDEX_HTML_FILE_PATH).toString());
    }
}
