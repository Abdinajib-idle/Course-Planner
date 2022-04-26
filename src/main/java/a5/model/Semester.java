/**
 * @author Abdinajib Idle && Abdinajib
 * */
package a5.model;

public class Semester {
    private static final int SEASON_CODE = 3;
    private static final int BASE_YEAR = 1900;

    String semesterCode;

    public Semester (String semesterCode) {
        this.semesterCode = semesterCode;
    }

    public String decipherSemesterCode () {
        String semester = semesterCode + " ";

        switch (semesterCode.charAt(SEASON_CODE)) {
            case '1':
                semester += "Spring ";
                break;
            case '4':
                semester += "Summer ";
                break;
            case '7':
                semester += "Fall ";
                break;
        }
        semester += Integer.toString(BASE_YEAR + Integer.parseInt(semesterCode.substring(0, semesterCode.length() - 1)));

        return semester;
    }

    public boolean equals (Semester other) {
        return semesterCode.equals(other.semesterCode);
    }


}
