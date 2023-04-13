package org.example;

import org.example.entity.Course;
import org.example.entity.Lesson;
import org.example.entity.Student;
import org.example.entity.University;
import org.example.repository.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate\\hibernate.cfg.xml");
        System.out.println(configuration);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        EntityManager entityManager = sessionFactory.createEntityManager();


        CoursesRepository coursesRepository = new CoursesRepositoryImpl(entityManager);
        LessonsRepository lessonsRepository = new LessonRepositoryImpl(entityManager);
        StudentRepository studentRepository = new StudentRepositoryImpl(entityManager);
        UniversityRepository universityRepository = new UniversityRepositoryImpl(entityManager);


        University university = University.builder()
                .name("SamGMU")
                .build();
        Student entity = Student.builder()
                .firstName("Petya")
                .lastName("Petrov")
                .build();

        Course course = Course.builder()
                .title("java 224")
                .students(Arrays.asList(entity))
                .university(university)
                .lessons(new ArrayList<Lesson>())
                .build();
        Lesson javacor = Lesson.builder()
                .course(course)
                .name("javaCore")
                .build();
        Lesson javaJDBC = Lesson.builder()
                .course(course)
                .name("javaJDBC")
                .build();
        Lesson javaScript = Lesson.builder()
                .course(course)
                .name("javaScript")
                .build();

        universityRepository.save(university);
        studentRepository.save(entity);

        coursesRepository.save(course);

        lessonsRepository.save(javacor);
        lessonsRepository.save(javaJDBC);
        lessonsRepository.save(javaScript);


    }
}
