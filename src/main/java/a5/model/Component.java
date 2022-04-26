/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.model;

public class Component {
    String componentCode;
    int capacity;
    int enrollmentTotal;

    int courseComponentId;

    public Component (String componentCode, int capacity, int enrollmentTotal) {
        this.componentCode = componentCode;
        this.capacity = capacity;
        this.enrollmentTotal = enrollmentTotal;
    }

    public void accumulateEnrollment (int capacity, int enrollmentTotal) {
        this.capacity += capacity;
        this.enrollmentTotal += enrollmentTotal;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getEnrollmentTotal() {
        return enrollmentTotal;
    }

    public void setEnrollmentTotal(int enrollmentTotal) {
        this.enrollmentTotal = enrollmentTotal;
    }

    public int getCourseComponentId() {
        return courseComponentId;
    }

    public void setCourseComponentId(int courseComponentId) {
        this.courseComponentId = courseComponentId;
    }
}