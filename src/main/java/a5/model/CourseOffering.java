/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CourseOffering implements Comparator<CourseOffering>, Comparable<CourseOffering> {
    Semester semester;
    String subject;
    String catalogNumber;
    String location;
    List<String> instructors;
    List<Component> componentList = new ArrayList<>();

    int deptIdForCourse;
    int courseId;
    int courseOfferingId;

    public CourseOffering(String semester, String subject, String catalogNumber, String location,
                          ArrayList<String> instructors, String componentCode, int capacity, int enrollmentTotal) {
        this.semester = new Semester(semester);
        this.subject = subject;
        this.catalogNumber = catalogNumber;
        this.location = location;
        this.instructors = instructors;
        componentList.add(new Component(componentCode, capacity, enrollmentTotal));
    }

    public static boolean isSameClass (CourseOffering a, CourseOffering b) {
        return a.subject.equals(b.subject) && a.catalogNumber.equals(b.catalogNumber)
                && a.location.equals(b.location) && a.semester.equals(b.semester);
    }

    public static boolean isSameCourseComponent (CourseOffering newCourse, CourseOffering existingCourse) {
        boolean componentAlreadyExists = false;
        for (Component component : existingCourse.componentList) {
            if (component.componentCode.equals(newCourse.componentList.get(0).componentCode)) {
                componentAlreadyExists = true;
                break;
            }
        }
        return newCourse.semester.equals(existingCourse.semester) && newCourse.subject.equals(existingCourse.subject)
                && newCourse.catalogNumber.equals(existingCourse.catalogNumber)
                && newCourse.location.equals(existingCourse.location) && componentAlreadyExists;
    }

    public void addInstructors (ArrayList<String> instructorList) {
        instructors.addAll(instructorList);
    }

    public void accumulateEnrollment (String componentCode, int capacity, int enrollmentTotal) {
        for (Component component : componentList) {
            if (component.componentCode.equals(componentCode)) {
                component.accumulateEnrollment(capacity, enrollmentTotal);
            }
        }
    }

    public void addComponent (String component, int capacity, int enrollmentTotal) {
        componentList.add(new Component(component, capacity, enrollmentTotal));
    }

    public String getAllInstructors () {
        String allInstructors = "";
        for (int i = 0; i < instructors.size(); i++) {
            allInstructors += instructors.get(i);
            if (i == instructors.size() - 1) {
                break;
            }
            allInstructors += ", ";
        }
        return allInstructors;
    }

    public String printAllComponents () {
        String allComponents = "";
        for (Component component : componentList) {
            allComponents += ("\n\t\tType=" + component.componentCode + ", Enrollment=" + component.enrollmentTotal + "/" + component.capacity);
        }
        return allComponents;
    }

    @Override
    public int compareTo(CourseOffering other) {
        int compareSubject = this.subject.compareTo(other.subject);

        String thisNumber = this.catalogNumber.replaceAll("[^\\d]", "");
        String otherNumber = other.catalogNumber.replaceAll("[^\\d]", "");

        if (compareSubject == 0) {
            return Integer.parseInt(thisNumber) - Integer.parseInt(otherNumber);
        }

        return compareSubject;
    }

    @Override
    public int compare(CourseOffering a, CourseOffering b) {
        int compareSubject = a.subject.compareTo(b.subject);

        String aNumber = a.catalogNumber.replaceAll("[^\\d]", "");
        String bNumber = b.catalogNumber.replaceAll("[^\\d]", "");

        if (compareSubject == 0) {
            return Integer.parseInt(aNumber) - Integer.parseInt(bNumber);
        }

        return compareSubject;
    }

    public String getSemester () {
        return semester.decipherSemesterCode();
    }

    public String getSubject() {
        return subject;
    }

    public int getDeptIdForCourse() {
        return deptIdForCourse;
    }

    public void setDeptIdForCourse(int deptIdForCourse) {
        this.deptIdForCourse = deptIdForCourse;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int deptIdForCourse) {
        this.courseId = deptIdForCourse;
    }

    public int getCourseOfferingId() {
        return courseOfferingId;
    }

    public void setCourseOfferingId(int courseOfferingId) {
        this.courseOfferingId = courseOfferingId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getInstructors() {
        return instructors;
    }

    public List<Component> getComponentList() {
        return componentList;
    }
}