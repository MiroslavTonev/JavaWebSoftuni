	package meTube.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meTube.domain.models.binding.TubeCreateBindingModel;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest req =  (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		if(req.getMethod().toLowerCase().equals("post")) {
			TubeCreateBindingModel tubeCreateBindingModel = new TubeCreateBindingModel();
			tubeCreateBindingModel.setName(req.getParameter("name"));
			tubeCreateBindingModel.setDescription(req.getParameter("description"));
			tubeCreateBindingModel.setYouTubeLink(req.getParameter("youTubeLink"));
			tubeCreateBindingModel.setUploader(req.getParameter("uploader"));	
			
			req.setAttribute("tubeCreateBindingModel", tubeCreateBindingModel);
		}				
		
		chain.doFilter(req, resp);
		
	}
	
}
