package chyshka.web.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import chyshka.domain.models.view.ProductDetailsViewModel;
import chyshka.service.ProductService;
import chyshka.util.HtmlReader;
import chyshka.util.ModelMapper;

@WebServlet("/products/details")
public class ProductDetailsServlet extends HttpServlet{
	
	private final static String PRODUCT_DETAILS_FILE_PATH = "C:\\Users\\mirot\\IdeaProjects\\chyshka\\src\\main\\resources\\views\\detailsProduct.html";
	private final ModelMapper modelMapper;
	private final HtmlReader htmlReader;
	private final ProductService productService;
	
	@Inject
	public ProductDetailsServlet(ModelMapper modelMapper, HtmlReader htmlReader, ProductService productService) {
		
		this.modelMapper = modelMapper;
		this.htmlReader = htmlReader;
		this.productService = productService;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProductDetailsViewModel productDetailsViewModel = this.modelMapper
				.map(this.productService.findProductByName(req.getQueryString().split("=")[1]), ProductDetailsViewModel.class);
		
		String htmlFileContent = this.htmlReader.readHtmlFile(PRODUCT_DETAILS_FILE_PATH)
				.replace("{{productName}}", productDetailsViewModel.getName())
				.replace("{{productDescription}}", productDetailsViewModel.getDescription())
				.replace("{{productType}}", productDetailsViewModel.getType());
		
		resp.getWriter().println(htmlFileContent);
		
	}
	
	
}
