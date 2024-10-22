package ecommerce.smallecomerce.service.User;

import ecommerce.smallecomerce.dto.LoginRequest;
import ecommerce.smallecomerce.dto.Response;
import ecommerce.smallecomerce.dto.UserDto;
import ecommerce.smallecomerce.entity.User;

public interface UserService {
    Response registerUser(UserDto registrationRequest);
    Response loginUser(LoginRequest loginRequest);
    Response getAllUsers();
    User getLoginUser();
    Response getUserInfoAndOrderHistory();
}
