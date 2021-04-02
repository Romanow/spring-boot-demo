package ru.digitalhabbits.spring.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserResponse {
    private Integer id;
    private String login;
    private String firstName;
    private String middleName;
    private String lastName;
    private String age;
    private AddressInfo address;
}
