package meTube.web.servlets;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import meTube.domain.models.binding.TubeCreateBindingModel;
import meTube.models.TubeServiceModel;
import meTube.service.TubeService;
import util.ModelMapper;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet{
	
	private final TubeService tubeService;
	private final ModelMapper modelMapper;
	
	
	@Inject
	public TubeCreateServlet(TubeService tubeService, ModelMapper modelMapper) {
		this.tubeService = tubeService;
		this.modelMapper = modelMapper;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/jsps/create-tubå.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TubeCreateBindingModel tubeCreateBindingModel = (TubeCreateBindingModel) req.getAttribute("tubeCreateBindingModel");
		
		this.tubeService.saveTube(this.modelMapper.map(tubeCreateBindingModel, TubeServiceModel.class));
		
		resp.sendRedirect("/tubes/details?name=" + tubeCreateBindingModel.getName());
	}
}
