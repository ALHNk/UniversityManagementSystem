package suharik.apps.universitymanagement.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import suharik.apps.universitymanagement.DTOs.AdminAddStudentDTO;
import suharik.apps.universitymanagement.DTOs.AdminAddTeacherDTO;
import suharik.apps.universitymanagement.DTOs.UserUpdateDTO;
import suharik.apps.universitymanagement.Services.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add/student")
    public ResponseEntity<?> addStudent(@Valid @RequestBody AdminAddStudentDTO userDTO) {
        userDTO.setRole("STUDENT");
        try{
            userService.createUser(userDTO);
            return ResponseEntity.ok("Student Added!");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PutMapping("/update/student/{id}")
    public ResponseEntity<?> updateStudent(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable int id) {
        try{
            userService.updateUser(userDTO, id);
            return ResponseEntity.ok("Student Updated!");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
    @DeleteMapping("/delete/student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Student Deleted!");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


    @PostMapping("/add/teacher")
    public ResponseEntity<?> addTeacher(@Valid @RequestBody AdminAddTeacherDTO userDTO) {
        userDTO.setRole("TEACHER");
        try{
            userService.createUser(userDTO);
            return ResponseEntity.ok("Teacher Added!");
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e){
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update/teacher/{id}")
    public ResponseEntity<?> updateTeacher(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable int id) {
        try{
            userService.updateUser(userDTO, id);
            return ResponseEntity.ok("Teacher Updated!");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/teacher/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Teacher Deleted!");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @PutMapping("/update/admin/{id}")
    public ResponseEntity<?> updateAdmin(@Valid @RequestBody UserUpdateDTO userDTO, @PathVariable int id) {
        try{
            userService.updateUser(userDTO, id);
            return ResponseEntity.ok("Admin Updated!");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/admin/{id}")
    public ResponseEntity<?> deleteAdmin(@PathVariable int id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Admin Deleted!");
        }catch (Exception e) {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }


}
