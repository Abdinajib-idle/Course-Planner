package a5.model.wrappers;

public class CourseInfoWrapper {
    String semesterCode;
    String term;
    String year;
    String instructors;
    String location;

    int courseId;
    int courseOfferingId;

    public CourseInfoWrapper(String semesterCode, String term, String year, String instructors, String location, int courseId, int courseOfferingId) {
        this.semesterCode = semesterCode;
        this.term = term;
        this.year = year;
        this.instructors = instructors;
        this.location = location;
        this.courseId = courseId;
        this.courseOfferingId = courseOfferingId;
    }

    public String getSemesterCode() {
        return semesterCode;
    }

    public String getTerm() {
        return term;
    }

    public String getYear() {
        return year;
    }

    public String getInstructors() {
        return instructors;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public String getLocation() {
        return location;
    }
}
