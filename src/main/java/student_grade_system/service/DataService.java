package student_grade_system.service;

import student_grade_system.entity.Student;
import student_grade_system.entity.Subject;
import student_grade_system.storage.InMemoryStorage;

import java.util.List;

public class DataService {
    private final InMemoryStorage<Student> students;
    private final InMemoryStorage<Subject> subjects;

    public DataService() {
        this.students = new InMemoryStorage<>();
        this.subjects = new InMemoryStorage<>();
    }

    public void registerStudent(Student student) {
        students.save(student);
    }

    public void registerSubject(Subject subject) {
        subjects.save(subject);
    }

    public List<Student> getAllStudents() {
        return students.loadAll();
    }
    public List<Subject> getAllSubjects() {
        return subjects.loadAll();
    }
}
