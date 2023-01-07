package pl.javastart.task;

import java.util.Arrays;

public class University {

    private static final int INIT_LECTURERS_CAPACITY = 20;
    private static final int INIT_STUDENT_CAPACITY = 20;
    private static final int INIT_GROUP_CAPACITY = 20;
    private int lecturerNumber = 0;
    private int studentNumber = 0;
    private int groupsNumber = 0;

    private Lecturer[] lecturers = new Lecturer[INIT_LECTURERS_CAPACITY];
    private Student[] students = new Student[INIT_STUDENT_CAPACITY];
    private Group[] groups = new Group[INIT_GROUP_CAPACITY];

    public void createLecturer(int id, String degree, String firstName, String lastName) {
        if (findLecturer(id) == null) {
            Lecturer lecturer = new Lecturer(id, degree, firstName, lastName);
            ensureLecturerCapacity();
            lecturers[lecturerNumber] = lecturer;
            lecturerNumber++;
        } else {
            System.out.println("Prowadzący z id " + id + " już istnieje");
        }
    }

    private void ensureLecturerCapacity() {
        if (lecturers.length == lecturerNumber) {
            lecturers = Arrays.copyOf(lecturers, lecturers.length * 2);
        }
    }

    public Student createStudent(int index, String groupCode, String firstName, String lastName) {
        Student student = null;
        if (findStudent(index) == null) {
            student = new Student(index, groupCode, firstName, lastName);
            ensureStudentCapacity();
            students[studentNumber] = student;
            studentNumber++;
        } else {
            System.out.println("Student z id " + index + " już istnieje");
        }
        return student;
    }

    private void ensureStudentCapacity() {
        if (students.length == studentNumber) {
            students = Arrays.copyOf(students, students.length * 2);
        }
    }

    public void createGroup(String code, String name, int lecturerId) {
        Group foundGroup = findGroup(code);
        Lecturer lecturer = findLecturer(lecturerId);
        if (foundGroup != null) {
            System.out.println("Grupa " + code + " już istnieje");
            return;
        }
        if (lecturer == null) {
            System.out.println("Prowadzący z id " + lecturerId + " nie istnieje");
        }
        if (lecturer != null) {
            if (groups.length == groupsNumber) {
                groups = Arrays.copyOf(groups, groups.length * 2);
            }
            Group group = new Group(code, name, lecturer);
            groups[groupsNumber] = group;
            groups[groupsNumber].lecturer = lecturer;
            groupsNumber++;
        }
    }

    public void addStudentToGroup(int index, String groupCode, String firstName, String lastName) {
        Group group = findGroup(groupCode);

        if (findGroup(groupCode) == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
            return;
        }
        Student student = findStudent(index);
        Student studentInGroup = group.findStudent(index);

        if (student == null) {
            student = createStudent(index, groupCode, firstName, lastName);
        }
        if (studentInGroup != null) {
            System.out.println("Student o indeksie " + index + " jest już w grupie " + groupCode);
        }
        if (studentInGroup == null) {
            group.addStudent(student);
        }

    }

    public void addGrade(int studentIndex, String groupCode, double grade) {
        Group group = findGroup(groupCode);
        int indexInArrayForStudent = 0;
        double gradeValue = 0;

        if (findGroup(groupCode) == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
            return;
        }
        Student studentInGroup = group.findStudent(studentIndex);
        indexInArrayForStudent = group.findArrayIndexForStudent(studentIndex);
        gradeValue = group.findStudentGrade(indexInArrayForStudent);

        if (studentInGroup == null) {
            System.out.println("Student o indeksie " + studentIndex + " nie jest zapisany do grupy " + groupCode);
        }
        if (studentInGroup != null && gradeValue != 0) {
            System.out.println("Student o indeksie " + studentIndex + " ma już wystawioną ocenę dla grupy " + groupCode);
        }
        if (studentInGroup != null && gradeValue == 0) {
            group.addGradeForStudent(grade, indexInArrayForStudent);
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
        if (group == null) {
            System.out.println("Grupa " + groupCode + " nie istnieje");
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
