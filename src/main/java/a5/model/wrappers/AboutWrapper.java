package a5.model.wrappers;

public class AboutWrapper {
    private String appName;
    private String authorName;
    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public AboutWrapper(String appName, String authorName) {
        this.appName = appName;
        this.authorName = authorName;
    }



}
