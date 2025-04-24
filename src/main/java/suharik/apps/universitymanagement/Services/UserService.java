package suharik.apps.universitymanagement.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;
import suharik.apps.universitymanagement.DTOs.AuthRequest;
import suharik.apps.universitymanagement.DTOs.UserDTO;
import suharik.apps.universitymanagement.DTOs.UserUpdateDTO;
import suharik.apps.universitymanagement.Entities.Admin;
import suharik.apps.universitymanagement.Entities.Student;
import suharik.apps.universitymanagement.Entities.Teacher;
import suharik.apps.universitymanagement.Entities.User;
import suharik.apps.universitymanagement.Repositories.UserRepository;

import javax.validation.constraints.Null;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;

    public void createUser(UserDTO userDTO)
    {
        User user = null;
        switch(userDTO.getRole()){
            case "ADMIN" ->{
                user = new Admin();
                fillUser(user, userDTO);
                break;
            }
            case "TEACHER" ->{
                user = new Teacher();
                fillUser(user, userDTO);
                break;
            }
            case "STUDENT" ->{
                Student student = new Student();
                fillUser(student, userDTO);
                student.setGroupName(userDTO.getGroupName());
                user = student;
//                userRepository.save(student);
                break;
            }
            default -> throw new IllegalArgumentException("Unknown role: " + userDTO.getRole());
        }

        userRepository.save(user);
    }

    public Map<String, String> loginUser(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User with email not found"));


        String accessToken = jwtService.generateToken(user);

        Map<String, String> tokens = new HashMap<>();
        tokens.put("access_token", accessToken);

        return tokens;
    }

    public void fillUser(User user, UserDTO userDTO) {
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
    }

    public List<User> getUsers(Class<? extends User> clazz) {
        try{
            List<User> users = userRepository.findUsersByRole(clazz);
            return users;
        }
        catch (Exception e)
        {
            throw Exceptions.propagate(e);
        }
    }

    public User getUser(int id) {
        try{
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
            return user;
        }
        catch (Exception e)
        {
            throw Exceptions.propagate(e);
        }
    }

    public void updateUser(UserUpdateDTO userDTO, int id) {
        try{
            User userToChange = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            if(userDTO.getEmail() != null){
                userToChange.setEmail(userDTO.getEmail());
            }
            if(userDTO.getFirstName() != null){
                userToChange.setFirstName(userDTO.getFirstName());
            }
            if(userDTO.getLastName() != null){
                userToChange.setLastName(userDTO.getLastName());
            }
            if(userDTO.getPassword() != null){
                userToChange.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            }
            userRepository.save(userToChange);
        }
        catch (Exception e)
        {
            throw Exceptions.propagate(e);
        }

    }
    public void deleteUser(int id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }


}
