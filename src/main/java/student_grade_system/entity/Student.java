package student_grade_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private final String firstName;
    private final String lastName;
    private List<Grade> grades;
    private List<Subject> subjects;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        grades = new ArrayList<>();
        subjects = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Grade> getGrades() {
        return List.copyOf(grades);
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }
    public void addGrades(Grade ...grade) {
        for (Grade g : grade) {
            addGrade(g);
        }
    }
    public List<Subject> getSubjects() {
        return List.copyOf(subjects);
    }
    public void addSubject(Subject subject) {
        if(!subjects.contains(subject)) {
            subjects.add(subject);
        }
    }
    public void addSubjects(Subject ...subject) {
        for (Subject s : subject) {
            addSubject(s);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equals(firstName, student.firstName) && Objects.equals(lastName, student.lastName) && Objects.equals(grades, student.grades) && Objects.equals(subjects, student.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, grades, subjects);
    }
}
