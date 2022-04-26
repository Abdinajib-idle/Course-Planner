/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.controllers;

import a5.model.wrappers.*;
import a5.model.CsvHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Controller {
    private CsvHandler data = new CsvHandler("CSV/course_data_2018.csv");

    @GetMapping("/about")
    public AboutWrapper functionName1 () {
        return new AboutWrapper("CMPT 213 Assignment-5 Course Planner Solution","Aryan & Abdinajib");
    }

    @GetMapping("/dump-model")
    public void dumpModel() {
        data.debugPrint();
        data.writeToFile();
    }

    @GetMapping("/departments")
    public List<DepartmentWrapper> listDepartmentsInDropdown () {
        return data.getAllDepartments();
    }

    @GetMapping("departments/{deptId}/courses")
    public List<CatalogWrapper> listAllCoursesInDept (@PathVariable("deptId") int deptId) {
        return data.getAllCoursesOfDept(deptId);
    }

    @GetMapping("departments/{deptId}/courses/{courseId}/offerings")
    public List<CourseInfoWrapper> listAllCourseSections (@PathVariable("deptId") int deptId, @PathVariable("courseId") int courseId) {
        return data.getAllCoursesOfCatalogNumber(deptId, courseId);
    }

    @GetMapping("departments/{deptId}/courses/{courseId}/offerings/{courseOfferingId}")
    public List<CourseComponentWrapper> listAllCourseComponents (@PathVariable("deptId") int deptId,
                                                      @PathVariable("courseId") int courseId,
                                                      @PathVariable("courseOfferingId") int courseOfferingId) {
        return data.getAllCourseComponents(deptId, courseId, courseOfferingId);
    }

    @PostMapping("/addoffering")
    @ResponseStatus(HttpStatus.CREATED)
    public void functionName7 () {

    }

    @GetMapping("/watchers")
    public void functionName8 () {

    }

    @PostMapping("/watchers")
    @ResponseStatus(HttpStatus.CREATED)
    public void functionName9 () {

    }

    @GetMapping("/watchers/{watcherId}")
    public void functionName10 (@PathVariable("watcherId") int watcherId) {

    }

    @DeleteMapping("/watchers/{watcherId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void functionName11 (@PathVariable("watcherId") int watcherId) {

    }
}
