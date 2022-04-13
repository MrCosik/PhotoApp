package pl.learning.photoappusers.controller;

import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;
import pl.learning.photoappusers.model.CreateUserRequest;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final Environment env;

    public UsersController(Environment env) {
        this.env = env;
    }

    @GetMapping("/status/check")
    public String status(){
        return "Working " + env.getProperty("local.server.port") ;
    }

    @PostMapping
    public String createUser(@RequestBody CreateUserRequest userRequest){
        return  "Create user ";
    }

}
