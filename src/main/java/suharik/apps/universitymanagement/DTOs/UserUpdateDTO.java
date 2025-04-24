package suharik.apps.universitymanagement.DTOs;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String password;
    private String phone;
    private String department;
    private String groupName;
}
