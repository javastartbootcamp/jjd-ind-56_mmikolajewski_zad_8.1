package pl.javastart.task;

public class Student extends Person {
    private int index;
    private Group group;

    public Student(int index, Group group, String firstName, String lastName) {
        super(firstName, lastName);
        this.index = index;
        this.group = group;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
