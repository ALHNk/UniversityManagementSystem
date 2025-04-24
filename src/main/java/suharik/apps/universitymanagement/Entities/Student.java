package suharik.apps.universitymanagement.Entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("STUDENT")
@Data
public class Student extends User {
    @Column()
    private String groupName;
    private String phone;

    @ManyToMany
    @JoinTable(
            name = "student_lessons",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "lesson_id")
    )
    private Set<Lesson> lessons = new HashSet<>();

    public void addLesson(Lesson lesson)
    {
        this.lessons.add(lesson);
    }
    public void removeLesson(Lesson lesson)
    {
        this.lessons.remove(lesson);
    }

}
