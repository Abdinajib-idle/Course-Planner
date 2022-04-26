package a5.model.wrappers;

public class DepartmentWrapper {
    private String name;
    private int deptId;

    public String getName() {
        return name;
    }

    public int getDeptId() {
        return deptId;
    }

    public DepartmentWrapper (String name, int deptId) {
        this.name = name;
        this.deptId = deptId;
    }

    public String getDeptDebug () {
        return deptId + ". " + name;
    }
}