package suharik.apps.universitymanagement.Entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Lessons")
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String title;
    private String speciality;
    @Column(nullable = false)
    private int teacherId;
    private String groupName;

}
