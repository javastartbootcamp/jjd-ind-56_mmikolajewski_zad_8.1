package pl.javastart.task;

public class Tester {
    public static void main(String[] args) {
        University university = new University();

        university.createLecturer(1, "dr", "Janusz", "Rataj");
        university.createLecturer(2, "dr", "Tomasz", "Policzył");
        System.out.println("1---------------------------------------");
        university.createGroup("pp-2022", "Podstawy Programowania", 1);
        university.createGroup("po-2022", "Programowanie Obiektowe", 2);
        university.createGroup("po-2022", "Programowanie Obiektowe", 5);
        System.out.println("2---------------------------------------");
        university.addStudentToGroup(179128, "po-2022", "Marcin", "Abacki");
        university.addStudentToGroup(179128, "pp-2022", "Marcin", "Abacki");
        university.addStudentToGroup(179127, "po-2022", "Adam", "Browarski");
        university.addStudentToGroup(179129, "po-2022", "Atomasz", "Byłsobie");
        university.addStudentToGroup(179129, "pk-2022", "Atomasz", "Byłsobie");
        System.out.println("3---------------------------------------");
        university.printGroupInfo("pp-2022");
        System.out.println("4---------------------------------------");
        university.printGroupInfo("po-2022");
        System.out.println("5---------------------------------------");
        university.printAllStudents();
        System.out.println("6---------------------------------------");
        university.addGrade(179128, "pp-2022", 5);
        university.addGrade(179128, "po-2022", 5.5);
        university.addGrade(179128, "pp-2022", 5);
        university.addGrade(179129, "pp-2022", 5);
        university.addGrade(179129, "po-2022", 3);
        System.out.println("7---------------------------------------");
        university.printGradesForStudent(179128);
        System.out.println("8---------------------------------------");
        university.printGradesForStudent(179129);
        System.out.println("9---------------------------------------");
        university.printGradesForGroup("pp-2022");
        System.out.println("10---------------------------------------");
        university.printGradesForGroup("po-2022");

    }
}
