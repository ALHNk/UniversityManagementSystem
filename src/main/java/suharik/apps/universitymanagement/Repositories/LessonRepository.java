package suharik.apps.universitymanagement.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import suharik.apps.universitymanagement.Entities.Lesson;

public interface LessonRepository extends JpaRepository<Lesson, Integer> {

}
