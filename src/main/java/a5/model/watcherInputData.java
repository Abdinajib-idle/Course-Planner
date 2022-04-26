/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.model;

public class watcherInputData {
    private int deptId;
    private int courseId;

    public watcherInputData() {
    }

    public watcherInputData(int deptId, int courseId) {
        this.deptId = deptId;
        this.courseId = courseId;
    }

    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
