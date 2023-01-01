package pl.javastart.task;

public class Group {
    private String code;
    private String name;
    private int lecturerId;
    Lecturer lecturer;

    private final int maxStudents = 20;
    private int studentsNumber = 0;
    private Student[] students = new Student[maxStudents];
    private int[] grades = new int[maxStudents];

    public Group(String code, String name, int lecturerId) {
        this.code = code;
        this.name = name;
        this.lecturerId = lecturerId;
    }

    public void addStudent(Student student) {
        if (studentsNumber < maxStudents) {
            students[studentsNumber] = student;
            studentsNumber++;
        } else {
            System.out.println("Nie można dodać więcej studentów");
        }
    }

    public Student findStudent(int index) {
        Student student = null;
        for (int i = 0; i < studentsNumber; i++) {
            if (students[i] != null & students[i].getIndex() == index) {
                student = students[i];
            }
        }
        return student;
    }

    public int findStudentIndex(int index) {
        int number = 0;
        for (int i = 0; i < studentsNumber; i++) {
            if (students[i] != null & students[i].getIndex() == index) {
                number = i;
            }
        }
        return number;
    }

    public void printInfo() {
        System.out.println("Kod: " + code + "");
        System.out.println("Nazwa: " + name + "");
        System.out.println("Prowadzący: " + lecturer.printInfo() + "");
        for (int i = 0; i < studentsNumber; i++) {
            if (students[i] != null) {
                System.out.println(students[i].printInfo());
            }
        }

    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    public int getMaxStudents() {
        return maxStudents;
    }

    public int getStudentsNumber() {
        return studentsNumber;
    }

    public void setStudentsNumber(int studentsNumber) {
        this.studentsNumber = studentsNumber;
    }

    public Student[] getStudents() {
        return students;
    }

    public void setStudents(Student[] students) {
        this.students = students;
    }
}
