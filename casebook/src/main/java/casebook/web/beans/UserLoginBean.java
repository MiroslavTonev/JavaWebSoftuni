package casebook.web.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;

import casebook.domain.models.binding.UserLoginBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;

@Named
@RequestScoped
public class UserLoginBean {
	
	private UserLoginBindingModel userLoginBindingModel;	
	@Inject
	private UserService userService;
	@Inject
	private ModelMapper modelMapper;
	
	public UserLoginBean() {
		// TODO Auto-generated constructor stub
	}

	public UserLoginBean(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostConstruct
	public void initModel() {
		this.userLoginBindingModel = new UserLoginBindingModel();
	}

	
	public UserLoginBindingModel getUserLoginBindingModel() {
		return userLoginBindingModel;
	}

	public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
		this.userLoginBindingModel = userLoginBindingModel;
	}

	public void login() throws IOException {
		UserServiceModel userServiceModel = this.userService.loginUser(this.modelMapper.map(this.userLoginBindingModel, UserServiceModel.class));
		
		if(userServiceModel == null) {
			throw new IllegalArgumentException("Something went wrong");	
		}
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		session.setAttribute("username", userServiceModel.getUsername());
		session.setAttribute("id", userServiceModel.getId());
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/home");
	}
		
}
