package casebook.web.beans;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserProfileViewModel;
import casebook.service.UserService;

@Named
@RequestScoped
public class UserProfileBean {
	private UserProfileViewModel model;
	@Inject
	private UserService userService;
	@Inject
	private ModelMapper modelMapper;
	
	public UserProfileBean() {
		// TODO Auto-generated constructor stub
	}
	
	public UserProfileBean(UserService userService, ModelMapper modelMapper) {		
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostConstruct
	private void initModel() {		
		
		String id = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("id");
		
		UserServiceModel userServiceModel = this.userService.getUserById(id);
		
		if(userServiceModel == null) {
			throw new IllegalArgumentException("Something went wrong");
		}else {
			this.model = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
		}
	}

	public UserProfileViewModel getModel() {
		return model;
	}

	public void setModel(UserProfileViewModel model) {
		this.model = model;
	}
	
	
	
}
