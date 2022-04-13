package pl.learning.photoappusers.service;

import pl.learning.photoappusers.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDetails);
}
