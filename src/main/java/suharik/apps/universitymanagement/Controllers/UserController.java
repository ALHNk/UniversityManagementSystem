package suharik.apps.universitymanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import suharik.apps.universitymanagement.DTOs.AuthRequest;
import suharik.apps.universitymanagement.DTOs.UserDTO;
import suharik.apps.universitymanagement.Entities.Student;
import suharik.apps.universitymanagement.Entities.Teacher;
import suharik.apps.universitymanagement.Entities.User;
import suharik.apps.universitymanagement.Services.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private UserService userService;


    @GetMapping()
    public ResponseEntity<?> trys() {
        return ResponseEntity.ok("HELLO");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserDTO dto)
    {
        try{
            userService.createUser(dto);
            return ResponseEntity.ok("User registered successfully");
        }
        catch (IllegalArgumentException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        Map<String, String> tokens = userService.loginUser(request);
        return ResponseEntity.ok(tokens);
    }

    @GetMapping("/get/students")
    public ResponseEntity<?> getStudents() {
        List<User> users = new ArrayList<User>();
        try{
            users = userService.getUsers(Student.class);
            return ResponseEntity.ok(users);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get/teachers")
    public ResponseEntity<?> getTeachers() {
        List<User> users = new ArrayList<User>();
        try{
            users = userService.getUsers(Teacher.class);
            return ResponseEntity.ok(users);
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/get/student/{id}")
    public ResponseEntity<?> getStudent(@PathVariable int id) {
        try {
            return ResponseEntity.ok( userService.getUser(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @GetMapping("/get/teacher/{id}")
    public ResponseEntity<?> getTeacher(@PathVariable int id) {
        try {
            return ResponseEntity.ok( userService.getUser(id));
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
