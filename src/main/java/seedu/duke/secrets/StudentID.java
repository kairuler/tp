package seedu.duke.secrets;

public class StudentID extends Secret {
    private String studentID;

    public StudentID(String name, String folderName, String studentID) {
        super(name, folderName);
        this.studentID = studentID;
    }
}
