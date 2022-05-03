package pl.learning.photoappusers.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import pl.learning.photoappusers.dto.UserDto;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDetails);
    UserDto getUserDetailsByEmail(String email);
}
