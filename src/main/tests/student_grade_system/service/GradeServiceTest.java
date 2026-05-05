package student_grade_system.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import student_grade_system.entity.Grade;
import student_grade_system.entity.Student;
import student_grade_system.entity.Subject;
import static org.assertj.core.api.Assertions.*;

import java.util.List;


class GradeServiceTest {
    private static DataService dataService;

    @BeforeAll
    static void setUp() {
        dataService = new DataService();

        var subject1 = new Subject("Geography", List.of("Subject1", "Subject2"));
        var subject2 = new Subject("Mathematics", List.of("Subject1_m", "Subject2_m"));
        var subject3 = new Subject("Physics", List.of("Subject1_p", "Subject2_p"));
        dataService.registerSubject(subject1);
        dataService.registerSubject(subject2);
        dataService.registerSubject(subject3);

        var student1 = new Student("Alex", "Novak");
        student1.addGrades(new Grade(5, subject1),
                new Grade(2, subject1),
                new Grade(3, subject2));
        student1.addSubjects(subject1, subject2);
        var student2 = new Student("Bob", "Michael");
        student2.addGrades(new Grade(3, subject2),
                new Grade(3, subject1),
                new Grade(3, subject2));
        student2.addSubjects(subject1, subject2);
        var student3 = new Student("Marlin", "Monroe");
        student3.addGrades(new Grade(2, subject2),
                new Grade(2, subject2),
                new Grade(3, subject2));
        student3.addSubject(subject2);

        dataService.registerStudent(student2);
        dataService.registerStudent(student1);
        dataService.registerStudent(student3);
    }

    @Test
    void shouldReturnSubjectsRating() {
        var gradeService = new GradeService(dataService);
        var subjectRating = gradeService.getRatingBySubject(dataService.getAllSubjects().get(0));

        assertThat(String.format("%.2f", subjectRating)).isEqualTo("3.33");
    }
    @Test
    void shouldReturnZeroForSubjectIfNoGradesForSubject() {
        var gradeService = new GradeService(dataService);
        var subjectRating = gradeService.getRatingBySubject(dataService.getAllSubjects().get(2));

        assertThat(subjectRating).isEqualTo(0.0);
    }


    @Test
    void shouldReturnLimitedListWithTopStudentsWithTheHighestRating() {
        var gradeService = new GradeService(dataService);
        var topStudents = gradeService.getTopStudents(2);

        assertThat(topStudents).hasSize(2)
                        .extracting(Student::getLastName)
                                .contains("Novak", "Michael");
    }

    @Test
    void shouldReturnListOfStudentsOrderedByRating() {
        var gradeService = new GradeService(dataService);
        var sortedList = gradeService.sortStudentByRating();

        assertThat(sortedList).hasSize(3);
        assertThat(sortedList.get(0).getLastName()).isEqualTo("Novak");
        assertThat(sortedList.get(sortedList.size()-1).getLastName()).isEqualTo("Monroe");

    }
}