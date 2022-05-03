package pl.learning.photoappusers.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserRequest {
    private String email;
    private String password;

}
