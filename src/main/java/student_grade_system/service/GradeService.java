package student_grade_system.service;

import student_grade_system.entity.Grade;
import student_grade_system.entity.Student;
import student_grade_system.entity.Subject;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GradeService {

    private DataService dataService;

    public GradeService(DataService dataService) {
        this.dataService = dataService;
    }

    public double getRatingBySubject(Subject subject) {
        var studentsWithTargetedSubject = dataService.getAllStudents()
                .stream()
                .filter(v -> v.getSubjects().contains(subject))
                .toList();
        double result = 0.0;
        int count = 0;
        for (Student student : studentsWithTargetedSubject) {
            for (Grade grade : student.getGrades()) {
                if (grade.subject().equals(subject)) {
                    result += grade.grade();
                    count++;
                }
            }
        }
        return count > 0 ? result / count : 0.0;
    }

    public List<Student> getTopStudents(int limit) {
        var ratings = getStudentsRating();
        return ratings.entrySet().stream()
                .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
                .limit(limit)
                .map(Map.Entry::getKey)
                .toList();
    }

    public List<Student> sortStudentByRating() {
        return getStudentsRating().entrySet()
                .stream()
                .sorted(Map.Entry.<Student, Double>comparingByValue().reversed())
                .map(Map.Entry::getKey)
                .toList();
    }

    private Map<Student, Double> getStudentsRating() {
        return dataService.getAllStudents()
                .stream()
                .collect(Collectors.toMap(v -> v, this::calculateRating));
    }

    private double calculateRating(Student student) {
        return student.getGrades()
                .stream()
                .collect(Collectors.averagingDouble((Grade::grade)));
    }


}
