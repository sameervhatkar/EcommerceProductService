package dev.sameer.ecommerceproductservice.Service;

import dev.sameer.ecommerceproductservice.Client.UserServiceClient;
import dev.sameer.ecommerceproductservice.DTO.LoginRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserServiceClient userServiceClient;

    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UserResponseDTO userResponseDTO = userServiceClient.login(loginRequestDTO);
        return userResponseDTO;
    }

    public boolean validate(String token) {
        return userServiceClient.validate(token);
    }
}
