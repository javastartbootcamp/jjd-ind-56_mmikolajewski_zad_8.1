package pl.javastart.task;

public class Student extends Person {
    private int index;
    private String groupCode;
    Grade grade;

    public Student(int index, String groupCode, String firstName, String lastName) {
        super(firstName, lastName);
        this.index = index;
        this.groupCode = groupCode;
    }

    public String printInfo() {
        return index + " " + getFirstName() + " " +  getLastName();
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }
}
