package pl.learning.photoappusers.impl;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Service;
import pl.learning.photoappusers.dto.UserDto;
import pl.learning.photoappusers.entity.User;
import pl.learning.photoappusers.repository.UsersRepository;
import pl.learning.photoappusers.service.UserService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    UsersRepository repository;

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        User user = modelMapper.map(userDetails, User.class);
        user.setEncryptedPassword("test");

        repository.save(user);

        return null;
    }
}
