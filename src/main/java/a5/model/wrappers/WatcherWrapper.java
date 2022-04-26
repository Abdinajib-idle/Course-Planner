package a5.model.wrappers;

import java.util.List;

//need a watch to implement an observer
public class WatcherWrapper {
    public int id;
    public DepartmentWrapper dept;
    public CourseInfoWrapper course;
    public List<String> events;

    public WatcherWrapper(int id, DepartmentWrapper dept, CourseInfoWrapper course, List<String> events) {
        this.id = id;
        this.dept = dept;
        this.course = course;
        this.events = events;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DepartmentWrapper getDept() {
        return dept;
    }

    public void setDept(DepartmentWrapper dept) {
        this.dept = dept;
    }

    public CourseInfoWrapper getCourse() {
        return course;
    }

    public void setCourse(CourseInfoWrapper course) {
        this.course = course;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }
}
