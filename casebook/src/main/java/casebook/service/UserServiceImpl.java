package casebook.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.apache.commons.codec.digest.DigestUtils;
import org.modelmapper.ModelMapper;

import casebook.domain.entities.User;
import casebook.domain.models.service.UserServiceModel;
import casebook.repository.UserRepository;

public class UserServiceImpl implements UserService {
	
	@Inject
	private final UserRepository userRepository;
	@Inject
	private final ModelMapper modelMapper;
	
	@Inject
	public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}
	

	@Override
	public boolean registerUser(UserServiceModel userServiceModel) {
		User user = this.modelMapper.map(userServiceModel, User.class);
		user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
		
		if(this.userRepository.save(user) == null) {			
			return false;
		}
		return true;
	}


	@Override
	public UserServiceModel loginUser(UserServiceModel userServiceModel) {
		User user = this.userRepository.findByUsername(userServiceModel.getUsername());
		
		if(user == null || !user.getPassword().equals(DigestUtils.sha256Hex(userServiceModel.getPassword()))) {
			return null;	
		}
		return this.modelMapper.map(user, UserServiceModel.class);
	}


	@Override
	public UserServiceModel getUserById(String id) {
		User user = this.userRepository.findById(id);
		if(user == null) {
			return null;
		}else {
			return this.modelMapper.map(user, UserServiceModel.class);
		}
		
	}


	@Override
	public List<UserServiceModel> getAllUsers() {
		return this.userRepository.findAll().stream().map(u -> this.modelMapper.map(u, UserServiceModel.class)).collect(Collectors.toList());
	}


	@Override
	public boolean addFriend(UserServiceModel userServiceModel) {
		User user = this.userRepository.update(this.modelMapper.map(userServiceModel, User.class));
		
		if(user != null) {
			return true;
		}
		
		return false;
	}
   
}
