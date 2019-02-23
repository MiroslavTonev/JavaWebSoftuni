package casebook.web.beans;

import java.io.IOException;
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
import casebook.domain.models.view.UserHomeViewModel;
import casebook.service.UserService;


@Named
@RequestScoped
public class HomeBean {
	
	private List<UserHomeViewModel> models;
	@Inject
	private UserService userService;
	@Inject
	private ModelMapper modelMapper;
	
	public HomeBean() {
		// TODO Auto-generated constructor stub
	}

	public HomeBean(UserService userService, ModelMapper modelMapper) {		
		this.userService = userService;
		this.modelMapper = modelMapper;
	}
	
	@PostConstruct
	public void initModels() {
		String username = (String)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("username");
		String id = (String)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getAttribute("id");
		UserServiceModel loggnedInUser = this.userService.getUserById(id);
		
		this.models = this.userService.getAllUsers()
				.stream()
				.filter(u -> !u.getUsername().equals(username) && !loggnedInUser.getFriends().stream().map(f -> f.getUsername()).collect(Collectors.toList()).contains(u.getUsername()))
				.map(u -> this.modelMapper.map(u, UserHomeViewModel.class)).collect(Collectors.toList());
	}

	public List<UserHomeViewModel> getModels() {
		return models;
	}

	public void setModels(List<UserHomeViewModel> models) {
		this.models = models;
	}
	
	public void addFriend(String id) throws IOException {
		UserServiceModel userServiceModel = this.userService.getUserById(id);
		UserServiceModel loggedInUser = this.userService.getUserById((String)((HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true)).getAttribute("id"));
		userServiceModel.getFriends().add(loggedInUser);
		
		loggedInUser.getFriends().add(userServiceModel);
		
		if(this.userService.addFriend(loggedInUser) == false) {
			throw new IllegalArgumentException("something went wrong");
		}
		
		if(this.userService.addFriend(userServiceModel) == false) {
			throw new IllegalArgumentException("something went wrong");
		}
		
		
		FacesContext.getCurrentInstance().getExternalContext().redirect("/home");		
	}
	
}
