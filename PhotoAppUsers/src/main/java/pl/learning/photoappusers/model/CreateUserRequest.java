package pl.learning.photoappusers.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {

    private String firstName;
    private String lastName;
    private String password;
    private String email;
}
