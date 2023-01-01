package pl.javastart.task;

public class University {
    private static final int MAX_LECTURERS = 100;
    private static final int MAX_STUDENTS = 100;
    private static final int MAX_GROUPS = 100;

    private static int lecturerNumber = 0;
    private static int studentNumber = 0;
    private static int groupsNumber = 0;

    private static Lecturer[] lecturers = new Lecturer[MAX_LECTURERS];
    private static Student[] students = new Student[MAX_STUDENTS];
    private static Group[] groups = new Group[MAX_GROUPS];

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (findLecturer(id) == null) {
            Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
            lecturers[lecturerNumber] = lecturer;
            lecturerNumber++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }
    }

    public static Student createStudent(int index, String groupCode, String firstName, String lastName) {
        Student student = null;
        if (findStudent(index) == null) {
            student = new Student(index, groupCode, firstName, lastName);
            students[studentNumber] = student;
            studentNumber++;
        } else {
            System.out.println("Student z id " + index + " już istnieje");
        }
        return student;
    }

    public void createGroup(String code, String name, int lecturerId) {
        Group isGroup = findGroup(code);
        Lecturer isLecturer = findLecturer(lecturerId);
        if (isGroup != null) {
            System.out.println("Grupa " + code + " już istnieje");
        }
        if (isLecturer == null) {
            System.out.println("Prowadzący z id " + lecturerId + " nie istnieje");
        }
        if (isGroup == null && isLecturer != null) {
            Group group = new Group(code, name, lecturerId);
            groups[groupsNumber] = group;
            groups[groupsNumber].lecturer = isLecturer;
            groupsNumber++;
        }
    }

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group isGroup = findGroup(groupCode);
        Student isStudent = findStudent(index);
        Student isStudentInGroup = isGroup.findStudent(index);

        if (isStudent == null) {
            isStudent = createStudent(index, groupCode, firstName, lastName);
        }
        if (isGroup != null && isStudentInGroup == null) {
            isGroup.addStudent(isStudent);
        }
        if (isGroup == null) {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
        }
        if (isStudentInGroup != null) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        }
    }

    public void addGrade(int studentIndex, String groupCode, double grade) {

    }

    private static Lecturer findLecturer(int id) {
        Lecturer lecturer = null;
        for (int i = 0; i < lecturerNumber; i++) {
            if (lecturers[i] != null & lecturers[i].getId() == id) {
                if (lecturers[i].getId() == id) {
                    lecturer = lecturers[i];
                }
            }
        }
        return lecturer;
    }

    private static Group findGroup(String groupCode) {
        Group group = null;
        for (int i = 0; i < groupsNumber; i++) {
            if (groups[i] != null & groups[i].getCode() == groupCode) {
                if (groups[i].getCode() == groupCode) {
                    group = groups[i];
                }
            }
        }
        return group;
    }

    private static Student findStudent(int index) {
        Student student = null;
        for (int i = 0; i < studentNumber; i++) {
            if (students[i] != null & students[i].getIndex() == index) {
                student = students[i];
            }
        }
        return student;
    }

//    private static int findStudentInGroup(int index) {
//        int i = 0;
//        for (int i = 0; i < studentNumber; i++) {
//            if (students[i] != null & students[i].getIndex() == index) {
//                student = students[i];
//            }
//        }
//        return student;
//    }

    public void printGroupInfo(String groupCode) {
        if (findGroup(groupCode) != null) {
            Group group = findGroup(groupCode);
            group.printInfo();
        } else {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
        }
    }

    public void printAllStudents() {
        for (int i = 0; i < studentNumber; i++) {
            System.out.println(students[i].getIndex()
                    + " " + students[i].getFirstName()
                    + " " + students[i].getLastName());

        }
    }
}
