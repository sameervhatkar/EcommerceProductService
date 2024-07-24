package dev.sameer.ecommerceproductservice.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserResponseDTO {
    private String userName;
    private String userEmail;
    private List<RoleResponseDTO> roleResponseDTOList;
    private String userToken;
}
