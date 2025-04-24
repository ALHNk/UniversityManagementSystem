package suharik.apps.universitymanagement.Controllers;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import suharik.apps.universitymanagement.DTOs.LessonDTO;
import suharik.apps.universitymanagement.Entities.User;
import suharik.apps.universitymanagement.Services.LessonService;

@Controller
public class LessonController {
    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @PostMapping("/admin/add/lesson")
    public ResponseEntity<?> addLesson(@RequestBody LessonDTO lessonDTO) {
        try
        {
            lessonService.createLesson(lessonDTO);
            return ResponseEntity.ok("Lesson created successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
        catch (Exception e)
        {
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/lessons")
    public ResponseEntity<?> getAllLessons() {
        try{
            return ResponseEntity.ok(lessonService.getLessons());
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lesson/{id}")
    public ResponseEntity<?> getLessonById(@PathVariable int id) {
        try{
            return ResponseEntity.ok(lessonService.getLessonOne(id));
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("enrolment/add/lesson/{lessonID}")
    public ResponseEntity<?> addStudentToLesson(@PathVariable int lessonID) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user  = (User) auth.getPrincipal();
            int studentID = user.getId();
            lessonService.addLessonForStudent(lessonID, studentID);
            return ResponseEntity.ok("Lesson added successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("enrolment/delete/lesson/{lessonID}")
    public ResponseEntity<?> deleteStudentToLesson(@PathVariable int lessonID) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user  = (User) auth.getPrincipal();
            int studentID = user.getId();
            lessonService.deleteLessonForStudent(lessonID, studentID);
            return ResponseEntity.ok("Lesson added successfully");
        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
