package ecommerce.smallecomerce.service.User;

import ecommerce.smallecomerce.dto.LoginRequest;
import ecommerce.smallecomerce.dto.Response;
import ecommerce.smallecomerce.dto.UserDto;
import ecommerce.smallecomerce.entity.User;
import ecommerce.smallecomerce.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    @Override
    public Response registerUser(UserDto registrationRequest) {

        return null;
    }

    @Override
    public Response loginUser(LoginRequest loginRequest) {
        return null;
    }

    @Override
    public Response getAllUsers() {
        return null;
    }

    @Override
    public User getLoginUser() {
        return null;
    }

    @Override
    public Response getUserInfoAndOrderHistory() {
        return null;
    }
}
