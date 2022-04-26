package a5.model.wrappers;

public class CatalogWrapper {
    String catalogNumber;
    int courseId;

    public CatalogWrapper(String catalogNumber, int courseId) {
        this.catalogNumber = catalogNumber;
        this.courseId = courseId;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public int getCourseId() {
        return courseId;
    }
}
