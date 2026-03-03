package studentmanagement;

public class StudentManagementException extends Exception {

    public StudentManagementException(String message) {
        super(message);
    }

    public StudentManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}