package suharik.apps.universitymanagement.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@DiscriminatorValue("TEACHER")
public class Teacher extends User {
    private String department;

    @OneToMany
    @JoinTable(
            name = "teacher_lessons",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<Lesson> lessons;

    public void addLesson(Lesson lesson) {
        this.lessons.add(lesson);
        lesson.setTeacherId(this.getId());
    }
    public void removeLesson(Lesson lesson) {
        this.lessons.remove(lesson);
    }
}
