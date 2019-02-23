package casebook.service;

import java.util.List;

import casebook.domain.models.service.UserServiceModel;

public interface UserService {

    boolean registerUser(UserServiceModel userServiceModel);
    
    UserServiceModel loginUser(UserServiceModel userServiceModel); 
    
    UserServiceModel getUserById(String id);
    
    List<UserServiceModel> getAllUsers();
    
    boolean addFriend(UserServiceModel userServiceModel);
}
