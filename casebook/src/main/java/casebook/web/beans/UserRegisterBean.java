package casebook.web.beans;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.modelmapper.ModelMapper;
import casebook.domain.models.binding.UserRegisterBindingModel;
import casebook.domain.models.service.UserServiceModel;
import casebook.service.UserService;

@Named
@RequestScoped
public class UserRegisterBean {
	
	private UserRegisterBindingModel model;
	@Inject
	private UserService userService;
	@Inject
	private ModelMapper modelMapper;
	
	public UserRegisterBean() {
		// TODO Auto-generated constructor stub
	}
	
	public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@PostConstruct
	private void initModel() {
		this.model = new UserRegisterBindingModel();
	}
	
	public UserRegisterBindingModel getModel() {
		return model;
	}

	public void setModel(UserRegisterBindingModel model) {
		this.model = model;
	}

	public void register() throws IOException {
		if(!this.model.getPassword().equals(this.model.getConfirmPassword())) {
			throw new IllegalArgumentException("Passwords do not match");
		}
		
		if(!this.userService.registerUser(this.modelMapper.map(this.model, UserServiceModel.class))) {
			throw new IllegalArgumentException("Something went wrong");
		}
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/login");
	}
	
}
