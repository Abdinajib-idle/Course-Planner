/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.model;

import a5.model.wrappers.CatalogWrapper;
import a5.model.wrappers.CourseComponentWrapper;
import a5.model.wrappers.CourseInfoWrapper;
import a5.model.wrappers.DepartmentWrapper;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvHandler {
    String line = "";
    static List<CourseOffering> allCourses = new ArrayList<>();
    static List<DepartmentWrapper> allDepartments = new ArrayList<>();

    private static final int COURSE_DATA_UP_TO_INSTRUCTORS = 6;

    BufferedReader file;

    public CsvHandler (String dataFile) {
        try {
            file = new BufferedReader(new FileReader(dataFile));
            boolean ignoreHeader = true;
            while((line = file.readLine()) != null){
                String[] fileContentsArray = line.split(",");
                ArrayList<String> instructors = new ArrayList<>();

                if (!ignoreHeader) {
                    for (int i = COURSE_DATA_UP_TO_INSTRUCTORS; i < fileContentsArray.length - 1; i++) {
                        instructors.add(fileContentsArray[i]);
                    }
                    addCourseOffering(fileContentsArray[0].trim(), fileContentsArray[1].trim(), fileContentsArray[2].trim(), fileContentsArray[3].trim(),
                            fileContentsArray[4].trim(), fileContentsArray[5].trim(), instructors, fileContentsArray[fileContentsArray.length - 1].trim());
                }
                ignoreHeader = false;
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        allCourses.sort(CourseOffering::compareTo);

        // Self Use
        generateDepartmentList();
        assignDeptIds();
        assignCourseIds();
    }

    private static void addCourseOffering (String semester, String subject, String catalogueNumber,
                                           String location, String capacity, String totalEnrollment,
                                           ArrayList<String> instructors, String componentCode) {
        CourseOffering newCourse = new CourseOffering(semester, subject, catalogueNumber, location, instructors,
                componentCode, Integer.parseInt(capacity), Integer.parseInt(totalEnrollment));

        for (CourseOffering existingCourse : allCourses) {
            if (CourseOffering.isSameCourseComponent(newCourse, existingCourse)) {
                instructors.removeAll(existingCourse.instructors);
                existingCourse.addInstructors(instructors);

                existingCourse.accumulateEnrollment(componentCode, Integer.parseInt(capacity), Integer.parseInt(totalEnrollment));
                return;
            }

            if (CourseOffering.isSameClass(newCourse, existingCourse)) {
                instructors.removeAll(existingCourse.instructors);
                existingCourse.addInstructors(instructors);

                existingCourse.addComponent(componentCode, Integer.parseInt(capacity), Integer.parseInt(totalEnrollment));
                return;
            }
        }
        allCourses.add(newCourse);
    }

    public static void generateDepartmentList () {
        String currentCourse = allCourses.get(0).subject;
        int deptId = 1;

        for (CourseOffering course : allCourses) {
            if (!course.subject.equals(currentCourse)) {
                allDepartments.add(new DepartmentWrapper(currentCourse, deptId));
                currentCourse = course.subject;
                deptId++;
            }
        }
        allDepartments.add(new DepartmentWrapper(currentCourse, deptId));
    }

    public static void assignDeptIds () {
        for (CourseOffering course : allCourses) {
            for (DepartmentWrapper dept : allDepartments) {
                if (course.getSubject().equals(dept.getName())) {
                    course.setDeptIdForCourse(dept.getDeptId());
                    break;
                }
            }
        }
    }

    public static void assignCourseIds () {
        int courseId = 0;
        int courseOfferingId = 0;
        String lastCourseNumber = "";

        for (CourseOffering course : allCourses) {
            if (!lastCourseNumber.equals(course.getCatalogNumber())) {
                lastCourseNumber = course.getCatalogNumber();
                courseId++;
            }
            course.setCourseId(courseId);

            courseOfferingId++;
            course.setCourseOfferingId(courseOfferingId);
        }
    }

    public List<DepartmentWrapper> getAllDepartments () {
        return allDepartments;
    }

    public List<CatalogWrapper> getAllCoursesOfDept (int deptId) {
        List<CatalogWrapper> coursesOfADept = new ArrayList<>();

        String lastCourseNumber = "";
        for (CourseOffering course : allCourses) {
            if (course.getDeptIdForCourse() == deptId
                && !lastCourseNumber.equals(course.getCatalogNumber())) {
                lastCourseNumber = course.getCatalogNumber();
                coursesOfADept.add(new CatalogWrapper(course.getCatalogNumber(), course.getCourseId()));
            }
        }
        return coursesOfADept;
    }

    public List<CourseInfoWrapper> getAllCoursesOfCatalogNumber (int deptId, int courseId) {
        List<CourseInfoWrapper> coursesOfANumber = new ArrayList<>();

        for (CourseOffering course : allCourses) {
            if (course.getDeptIdForCourse() == deptId
                    && courseId == course.getCourseId()) {

                String[] semesterInfo = course.getSemester().split(" ");

                coursesOfANumber.add(new CourseInfoWrapper(semesterInfo[0],semesterInfo[1],semesterInfo[2],
                        course.getAllInstructors(), course.getLocation(), course.getCourseId(), course.getCourseOfferingId()));
            }
        }

        return coursesOfANumber;
    }

    public List<CourseComponentWrapper> getAllCourseComponents (int deptId, int courseId, int courseOfferingId) {
        List<CourseComponentWrapper> myComponents = new ArrayList<>();

        try {
            CourseOffering offering = null;

            for (CourseOffering course : allCourses) {
                if (course.getCourseOfferingId() == courseOfferingId) {
                    offering = course;
                    break;
                }
            }

            int courseComponentId = 0;
            for (Component component : offering.getComponentList()) {
                component.setCourseComponentId(courseComponentId);
                myComponents.add(new CourseComponentWrapper(component.getComponentCode(),
                        component.getEnrollmentTotal(), component.getCapacity(), component.getCourseComponentId()));
                courseComponentId++;
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return myComponents;
    }

    public void debugPrint () {
        String lastCourseName = "";
        for (CourseOffering course : allCourses) {
            if (!lastCourseName.equals(course.subject + " " + course.catalogNumber)) {
                System.out.print("\n" + course.subject + " " + course.catalogNumber);
                lastCourseName = course.subject + " " + course.catalogNumber;
            }
            System.out.print("\n\t" + course.getSemester() + " in " + course.location + " by " + course.getAllInstructors() + course.printAllComponents());
        }

        System.out.println();
        for (DepartmentWrapper department : allDepartments) {
            System.out.println(department.getDeptDebug());
        }
    }

    public void writeToFile () {
        FileWriter fileOut;

        try {
            fileOut = new FileWriter("text_output/output.txt");
            String lastCourseName = "";
            for (CourseOffering course : allCourses) {
                if (!lastCourseName.equals(course.subject + " " + course.catalogNumber)) {
                    fileOut.write("\n" + course.subject + " " + course.catalogNumber);
                    lastCourseName = course.subject + " " + course.catalogNumber;
                }
                fileOut.write("\n\t" + course.getSemester() + " in " + course.location + " by " + course.getAllInstructors() + course.printAllComponents());
            }
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}