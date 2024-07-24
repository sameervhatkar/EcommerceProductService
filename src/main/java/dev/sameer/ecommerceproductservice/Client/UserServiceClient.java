package dev.sameer.ecommerceproductservice.Client;


import dev.sameer.ecommerceproductservice.DTO.LoginRequestDTO;
import dev.sameer.ecommerceproductservice.DTO.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @Value("${EcommerceUserService.base.url}")
    private String userServiceClientApiBaseUrl;

    public boolean validate(String token) {
        String userServiceClientBaseUrl = userServiceClientApiBaseUrl.concat("/validate");
        RestTemplate restTemplate = restTemplateBuilder.build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<Boolean> responseEntity = restTemplate.exchange(userServiceClientBaseUrl, HttpMethod.GET, entity,Boolean.class);
        return responseEntity.getBody();
    }


    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        String userServiceClientBaseUrl = userServiceClientApiBaseUrl.concat("/login");
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<UserResponseDTO> responseEntity = restTemplate.postForEntity(
                userServiceClientBaseUrl, loginRequestDTO, UserResponseDTO.class
        );
        return responseEntity.getBody();
    }

}

/*
public FakeStoreProductResponseDTO getProductById(int id){
        // url - https://fakestoreapi.com/products/id
        String fakeStoreGetProductURL = fakeStoreAPIBaseUrl.concat(fakeStoreAPIProductPath).concat("/" + id);
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> productResponse =
                restTemplate.getForEntity(fakeStoreGetProductURL, FakeStoreProductResponseDTO.class);
        return productResponse.getBody();
    }
 */
