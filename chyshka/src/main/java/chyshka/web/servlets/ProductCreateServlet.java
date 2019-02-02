package chyshka.web.servlets;

import java.io.IOException;
import java.util.Arrays;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chyshka.domain.entities.Type;
import chyshka.domain.models.service.ProductServiceModel;
import chyshka.service.ProductService;
import chyshka.util.HtmlReader;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet{
	
	private final static String CREATE_HTML_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\chyshka\\src\\main\\resources\\views\\createProductHtml.html"; 
	private final ProductService productService;
	private final HtmlReader htmlReader;
	
	@Inject
	public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {		
		this.productService = productService;
		this.htmlReader = htmlReader;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String htmlFileContent = this.htmlReader.readHtmlFile(CREATE_HTML_FILE_PATH)
				.replace("{{typeOptions}}", formatTypeOptions());
		
		resp.getWriter().println(htmlFileContent);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductServiceModel productServiceModel = new ProductServiceModel();
		productServiceModel.setName(req.getParameter("name"));
		productServiceModel.setDescription(req.getParameter("description"));
		productServiceModel.setType(req.getParameter("type"));	
		
		this.productService.saveProduct(productServiceModel);
		
		resp.sendRedirect("/");
		
	}
	
	private String formatTypeOptions() {
		StringBuilder options = new StringBuilder();
		
		Arrays.stream(Type.values()).forEach(type -> {
			options.append(String.format("<option value = \"%s\">%s</option>", type.name(), type.name())).append(System.lineSeparator());
		});
		
		return options.toString();
	}
	
}
