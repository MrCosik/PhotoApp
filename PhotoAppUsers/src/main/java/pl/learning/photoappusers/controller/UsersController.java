package pl.learning.photoappusers.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.learning.photoappusers.dto.UserDto;
import pl.learning.photoappusers.model.CreateUserRequest;
import pl.learning.photoappusers.model.CreateUserResponseRequest;
import pl.learning.photoappusers.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final Environment env;
    private final UserService userService;


    @GetMapping("/status/check")
    public String status(){
        return "Working " + env.getProperty("local.server.port")  + env.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseRequest> createUser(@RequestBody CreateUserRequest userRequest){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userRequest, UserDto.class);
        UserDto createdUser =  userService.createUser(userDto);

        createdUser.setUserId(UUID.randomUUID().toString());

        CreateUserResponseRequest returnValue = modelMapper.map(createdUser, CreateUserResponseRequest.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
