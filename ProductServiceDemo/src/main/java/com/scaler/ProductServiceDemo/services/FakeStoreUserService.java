package com.scaler.ProductServiceDemo.services;

import com.scaler.ProductServiceDemo.dtos.FakeStoreProductDto;
import com.scaler.ProductServiceDemo.dtos.FakeStoreUserDto;
import com.scaler.ProductServiceDemo.models.User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class FakeStoreUserService implements UserService {
    RestTemplate restTemplate;

    public FakeStoreUserService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public User getSingleUser(Long userId) {
        FakeStoreUserDto fakeStoreUserDto = restTemplate.getForObject(
                "https://fakestoreapi.com/users/" + userId,
                FakeStoreUserDto.class
        );

        return convertFakeStoreUserToUser(fakeStoreUserDto);
    }

    @Override
    public List<User> getAllUsers() {
        FakeStoreUserDto[] fakeStoreUserDtos = restTemplate.getForObject(
                "https://fakestoreapi.com/users",
                FakeStoreUserDto[].class
        );
        List<User> users = new ArrayList<>();
        for (FakeStoreUserDto fakeStoreUserDto : fakeStoreUserDtos) {
            users.add(convertFakeStoreUserToUser(fakeStoreUserDto));
        }

        return users;
    }

    private User convertFakeStoreUserToUser(FakeStoreUserDto fakeStoreUserDto) {
        User user = new User();
        user.setId(fakeStoreUserDto.getId());
        user.setUsername(fakeStoreUserDto.getUsername());
        user.setEmail(fakeStoreUserDto.getEmail());
        user.setPassword(fakeStoreUserDto.getPassword());
        return user;
    }
}
