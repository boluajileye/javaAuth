package bolu.ajileye.authfinal.repository;

import bolu.ajileye.authfinal.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, String> {
}