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

    public boolean validate(String jwtToken) {
        return userServiceClient.validate(jwtToken);
    }
}
