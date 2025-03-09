import java.io.*;
import java.util.Date;

/**
 * Enum defineste tipurile proiectelor
 */
enum Types {
    THEORETICAL,
    PRACTICAL
}

public class Main {
    /**
     * Metoda principala care creeaza obiecte din clase,
     * @param args Fara argumente
     */
    public static void main(String[] args) {
        Student student1 = new Student("Andrei Popescu", 5041020L, new Date());
        Student student2 = new Student("Gica Popescu", 5042030L, new Date());



        Project project1 = new Project("Formula I", Types.PRACTICAL);
        Project project2 = new Project("Formula II", Types.THEORETICAL);

        Teacher teacher1 = new Teacher("Ion Ionescu",new Date(), project1);
        Teacher teacher2 = new Teacher("Maria Pop", new Date(), project2);

        Project project3 = new Project("Formula III", Types.PRACTICAL);
        Project project4 = new Project("Formula IV", Types.THEORETICAL);

        teacher1.addProject(project3);
        teacher2.addProject(project4);

        Problem problem = new Problem(10, 10);
        problem.addStudent(student1);
        problem.addStudent(student2);
        problem.addTeacher(teacher1);
        problem.addTeacher(teacher2);

        problem.allocateProjects();
    }

}