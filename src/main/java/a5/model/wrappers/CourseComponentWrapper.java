package a5.model.wrappers;

public class CourseComponentWrapper {
    String type;
    int enrollmentTotal;
    int enrollmentCap;

    int courseOfferingId;

    public CourseComponentWrapper(String type, int enrollmentTotal, int enrollmentCap, int courseOfferingId) {
        this.type = type;
        this.enrollmentTotal = enrollmentTotal;
        this.enrollmentCap = enrollmentCap;
        this.courseOfferingId = courseOfferingId;
    }

    public String getType() {
        return type;
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(int courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public int getEnrollmentCap() {
        return enrollmentCap;
    }
}
