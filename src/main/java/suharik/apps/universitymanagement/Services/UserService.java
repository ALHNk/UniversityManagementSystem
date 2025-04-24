package suharik.apps.universitymanagement.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import suharik.apps.universitymanagement.DTOs.UserDTO;
import suharik.apps.universitymanagement.Entities.Admin;
import suharik.apps.universitymanagement.Entities.Student;
import suharik.apps.universitymanagement.Entities.Teacher;
import suharik.apps.universitymanagement.Entities.User;
import suharik.apps.universitymanagement.Repositories.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createUser(UserDTO userDTO)
    {
        User user = null;
        switch(userDTO.getRole()){
            case "ADMIN" ->{
                user = new Admin();
                user.setEmail(userDTO.getEmail());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                break;
            }
            case "TEACHER" ->{
                user = new Teacher();
                user.setEmail(userDTO.getEmail());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                break;
            }
            case "STUDENT" ->{
                user = new Student();
                user.setEmail(userDTO.getEmail());
                user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
                break;
            }
            default -> throw new IllegalArgumentException("Unknown role: " + userDTO.getRole());
        }

        userRepository.save(user);
    }

}
