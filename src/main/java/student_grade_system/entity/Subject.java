package student_grade_system.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Subject {
    private String name;
    private List<String> topics;

    public Subject(String name) {
        this.name = name;
        this.topics = new ArrayList<>();
    }

    public Subject(String name, List<String> topics) {
        this.name = name;
        this.topics = topics;
    }

    public String getName() {
        return name;
    }

    public void addTopic(String topic) {
        topics.add(topic);
    }

    public List<String> getTopics() {
        return topics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Subject subject = (Subject) o;
        return Objects.equals(name, subject.name) && Objects.equals(topics, subject.topics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, topics);
    }
}
