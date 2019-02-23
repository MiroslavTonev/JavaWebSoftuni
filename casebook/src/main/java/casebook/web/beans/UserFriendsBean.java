package casebook.web.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;

import casebook.domain.models.service.UserServiceModel;
import casebook.domain.models.view.UserFriendsViewModel;
import casebook.service.UserService;

@Named
@RequestScoped
public class UserFriendsBean {
	
	private List<UserFriendsViewModel> models;	
	@Inject
	private UserService userService;
	@Inject
	private ModelMapper modelMapper;
	
	public UserFriendsBean() {
		// TODO Auto-generated constructor stub
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ModelMapper getModelMapper() {
		return modelMapper;
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
	}
	
	@PostConstruct
	public void initModels() {
		this.models = this.userService.getUserById(
				(String)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("id"))
				.getFriends().stream().map(f -> this.modelMapper.map(f, UserFriendsViewModel.class)).collect(Collectors.toList());
	}

	public List<UserFriendsViewModel> getModels() {
		return models;
	}

	public void setModels(List<UserFriendsViewModel> models) {
		this.models = models;
	}
	
	public void removeFriend(String id) {
		
	}
}
