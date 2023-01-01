package pl.javastart.task;

public class University {
    private static final int MAX_LECTURERS = 200;
    private static final int MAX_STUDENTS = 200;
    private static final int MAX_GROUPS = 200;

    private static int lecturerNumber = 0;
    private static int studentNumber = 0;
    private static int groupsNumber = 0;

    private Lecturer[] lecturers = new Lecturer[MAX_LECTURERS];
    private Student[] students = new Student[MAX_STUDENTS];
    private Group[] groups = new Group[MAX_GROUPS];

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (findLecturer(id) == null) {
            Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
            lecturers[lecturerNumber] = lecturer;
            lecturerNumber++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }
    }

    public Student createStudent(int index, String groupCode, String firstName, String lastName) {
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
        Group isGroup = null;
        Student isStudent = null;
        Student isStudentInGroup = null;

        if (findGroup(groupCode) == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else {
            isGroup = findGroup(groupCode);
            isStudent = findStudent(index);
            isStudentInGroup = isGroup.findStudent(index);
        }
        if (isStudent == null) {
            isStudent = createStudent(index, groupCode, firstName, lastName);
        }
        if (isStudentInGroup != null) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        }
        if (isGroup != null && isStudentInGroup == null) {
            isGroup.addStudent(isStudent);
        }

    }

    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group isGroup = null;
        Student isStudentInGroup = null;
        int i = 0;
        double isGrade = 0;

        if (findGroup(groupCode) == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
        } else {
            isGroup = findGroup(groupCode);
            isStudentInGroup = isGroup.findStudent(studentIndex);
            i = isGroup.findStudentIndex(studentIndex);
            isGrade = isGroup.findStudentGrade(i);
        }
        if (isStudentInGroup == null) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
        }
        if (isStudentInGroup != null && isGrade != 0) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
        }
        if (isStudentInGroup != null && isGrade == 0) {
            isGroup.addGradeForStudent(grade, i);
        }
    }

    public void printGradesForStudent(int index) {
        for (int i = 0; i < groupsNumber; i++) {
            if (groups[i] != null) {
                for (int j = 0; j < groups[i].getStudentsNumber(); j++) {
                    if (groups[i].getStudents()[j].getIndex() == index) {
                        groups[i].printStudentGradeForThisGroup(j);
                    }
                }
            }
        }
    }

    public void printGradesForGroup(String groupCode) {
        Group isGroup = findGroup(groupCode);
        if (isGroup != null) {
            for (int i = 0; i < groupsNumber; i++) {
                if (groups[i] != null && groups[i].getCode() == groupCode) {
                    groups[i].printStudentInfoAndGradeForThisGroup();
                }
            }
        } else {
            System.out.println("Grupa pp-2022 nie istnieje");
        }
    }

    private Lecturer findLecturer(int id) {
        Lecturer lecturer = null;
        for (int i = 0; i < lecturerNumber; i++) {
            if (lecturers[i] != null && lecturers[i].getId() == id) {
                lecturer = lecturers[i];
            }
        }
        return lecturer;
    }

    private Group findGroup(String groupCode) {
        Group group = null;
        for (int i = 0; i < groupsNumber; i++) {
            if (groups[i] != null && groups[i].getCode() == groupCode) {
                group = groups[i];
            }
        }
        return group;
    }

    private Student findStudent(int index) {
        Student student = null;
        for (int i = 0; i < studentNumber; i++) {
            if (students[i] != null && students[i].getIndex() == index) {
                student = students[i];
            }
        }
        return student;
    }

    public void printGroupInfo(String groupCode) {
        if (findGroup(groupCode) != null) {
            Group group = findGroup(groupCode);
            group.printGroupInfo();
        } else {
            System.out.println("Grupa " + groupCode + " nie znaleziona");
        }
    }

    public void printAllStudents() {
        for (int i = 0; i < studentNumber; i++) {
            if (students[i] != null) {
                System.out.println(students[i].getIndex()
                        + " " + students[i].getFirstName()
                        + " " + students[i].getLastName());
            }
        }
    }
}
