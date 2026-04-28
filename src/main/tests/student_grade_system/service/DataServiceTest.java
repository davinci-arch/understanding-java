package student_grade_system.service;


import org.junit.jupiter.api.Test;
import student_grade_system.entity.Student;
import student_grade_system.entity.Subject;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DataServiceTest {

    @Test
    void shouldReturnRegisteredStudents() {
        var student1 = new Student("Alex", "Novak");
        var student2 = new Student("Bob", "Michael");

        var dataService = new DataService();
        dataService.registerStudent(student1);
        dataService.registerStudent(student2);

        var students = dataService.getAllStudents();

        assertThat(students).hasSize(2)
                        .contains(student1, student2);
        assertThat(students.get(0).getLastName()).isEqualTo(student1.getLastName());
        assertThat(students.get(1).getFirstName()).isEqualTo(student2.getFirstName());
    }

    @Test
    void shouldNotSaveStudentThatAlreadyRegistered() {
        var student1 = new Student("Alex", "Novak");
        var student2 = new Student("Bob", "Michael");
        var student3 = new Student("Alex", "Novak");

        var dataService = new DataService();
        dataService.registerStudent(student1);
        dataService.registerStudent(student2);
        dataService.registerStudent(student3);

        var students = dataService.getAllStudents();
        assertThat(students).hasSize(2)
                        .contains(student1, student2);
        assertThat(students.get(0).getLastName()).isEqualTo(student1.getLastName());
        assertThat(students.get(1).getFirstName()).isEqualTo(student2.getFirstName());

    }

    @Test
    void shouldReturnRegisteredSubjects() {
        var subject1 = new Subject("Geography", List.of("Subject1", "Subject2"));
        var subject2 = new Subject("Mathematics", List.of("Subject1_m", "Subject2_m"));

        var dataService = new DataService();
        dataService.registerSubject(subject1);
        dataService.registerSubject(subject2);

        var subjects = dataService.getAllSubjects();
        assertEquals(2, subjects.size());
        assertThat(subjects).hasSize(2)
                .contains(subject1, subject2);
        assertThat(subjects.get(0).getName()).isEqualTo(subject1.getName());
        assertThat(subjects.get(1).getName()).isEqualTo(subject2.getName());
    }

    @Test
    void shouldNotSaveSubjectThatAlreadyRegistered() {
        var subject1 = new Subject("Geography", List.of("Subject1", "Subject2"));
        var subject2 = new Subject("Mathematics", List.of("Subject1_m", "Subject2_m"));
        var subject3 = new Subject("Mathematics", List.of("Subject1_m", "Subject2_m"));

        var dataService = new DataService();
        dataService.registerSubject(subject1);
        dataService.registerSubject(subject2);
        dataService.registerSubject(subject3);
        var subjects = dataService.getAllSubjects();
        assertEquals(2, subjects.size());
        assertThat(subjects).hasSize(2)
                .contains(subject1, subject2);
        assertThat(subjects.get(0).getName()).isEqualTo(subject1.getName());
        assertThat(subjects.get(1).getName()).isEqualTo(subject2.getName());
    }
}
