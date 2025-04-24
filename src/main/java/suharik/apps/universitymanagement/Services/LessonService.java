package suharik.apps.universitymanagement.Services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.Exceptions;
import suharik.apps.universitymanagement.DTOs.LessonDTO;
import suharik.apps.universitymanagement.Entities.Lesson;
import suharik.apps.universitymanagement.Entities.Student;
import suharik.apps.universitymanagement.Repositories.LessonRepository;
import suharik.apps.universitymanagement.Repositories.UserRepository;

import java.util.List;

@Service
public class LessonService {
    private final LessonRepository lessonRepository;
    private final UserRepository userRepository;

    @Autowired
    public LessonService(LessonRepository lessonRepository, UserRepository userRepository) {
        this.lessonRepository = lessonRepository;
        this.userRepository = userRepository;
    }

    public void createLesson(LessonDTO dto)
    {
        try{
            Lesson lesson = new Lesson();
            lesson.setTitle(dto.getTitle());
            lesson.setSpeciality(dto.getSpeciality());
            lesson.setTeacherId(dto.getTeacherId());
            lessonRepository.save(lesson);
        }catch (EntityNotFoundException e)
        {
            throw Exceptions.propagate(e);
        }
        catch (Exception e)
        {
            throw Exceptions.propagate(e);
        }
    }

    public List<Lesson> getLessons()
    {
        return lessonRepository.findAll();
    }

    public Lesson getLessonOne(int id)
    {
        return lessonRepository.findById(id).orElse(null);
    }

    public void updateLesson(LessonDTO dto, int id)
    {
        Lesson lesson = lessonRepository.findById(id).orElse(null);
        if(dto.getTeacherId() > 0)
        {
            lesson.setTeacherId(dto.getTeacherId());
        }
        if(dto.getTitle() != null)
        {
            lesson.setTitle(dto.getTitle());
        }
        if (dto.getSpeciality() != null)
        {
            lesson.setSpeciality(dto.getSpeciality());
        }
        lessonRepository.save(lesson);
    }

    public void deleteLesson(int id)
    {
        lessonRepository.deleteById(id);
    }

    public void addLessonForStudent(int lessonId, int studentId)
    {
        Student student = (Student)userRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        student.addLesson(lesson);
        userRepository.save(student);
    }
    public void deleteLessonForStudent(int lessonId, int studentId)
    {
        Student student = (Student)userRepository.findById(studentId).orElseThrow(EntityNotFoundException::new);
        Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
        student.removeLesson(lesson);
        userRepository.save(student);
    }
}
