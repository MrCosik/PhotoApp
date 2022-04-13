package pl.learning.photoappusers.impl;

import pl.learning.photoappusers.dto.UserDto;
import pl.learning.photoappusers.service.UserService;

import java.util.UUID;

public class UserServiceImpl implements UserService {
    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
