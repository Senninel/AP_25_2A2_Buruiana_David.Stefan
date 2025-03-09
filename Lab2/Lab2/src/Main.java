import java.io.*;
import java.util.Date;

enum Types {
    THEORETICAL,
    PRACTICAL
}
abstract class Person
{
    String name;
    Date dob;
}
class Teacher extends Person
{
    Project[] projectList = new Project[105];

    public Teacher(String name, Date dob, Project project)
    {
        this.name = name;
        this.dob = dob;
        this.projectList = new Project[105];
        projectList[0] = project;
    }
    public void addProject(Project project) {
        for(int i=0;i<projectList.length;i++)
            {
            if(projectList[i]==null)
            {
                projectList[i]=project;
                return;
            }
            }
        System.out.println("Nu am putut adauga proiectul, e full lista");
    }
    public void removeProject(Project project) {
        for (int i = 0; i < projectList.length; i++) {
            if (projectList[i] != null && projectList[i].equals(project)) {
                projectList[i] = null;
                return;
            }
        }
    }
    @Override
        public String toString(){
         StringBuilder s = new StringBuilder();
         for(int i=0;i<projectList.length;i++)
             if(projectList[i]!=null)
                 s.append(projectList[i].toString()+"\n");
         return "Profesorul " + this.name + " are urmatoarele proiecte " + s;
    }
    @Override
     public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Teacher teacher = (Teacher) obj;

        StringBuilder firstTeacherList = new StringBuilder();
        for(int i=0;i<this.projectList.length;i++)
            if(this.projectList[i] != null)
                firstTeacherList.append(this.projectList[i].toString()+"\n");
        StringBuilder  secondTeacherList = new StringBuilder();
        for(int i=0;i<teacher.projectList.length;i++)
            if(teacher.projectList[i] != null)
                secondTeacherList.append(teacher.projectList[i].toString()+"\n");

        return  this.name.equals(teacher.name) && firstTeacherList.toString().equals(secondTeacherList.toString());
    }
    @Override
     public int hashCode(){
        int result = name.hashCode();
        for(int i=0;i<projectList.length && projectList[i] != null;i++)
            result+=projectList[i].hashCode();
        return result;
    }
}
class Student  extends Person
{
    private Long regNumber;
    public Student(String name, Long regNumber, Date dob) {
        this.name = name;
        this.regNumber = regNumber;
        this.dob = dob;
    }
    public Student()
    {
        this.name = null;
        this.regNumber = null;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }
    public Long getRegNumber() {
        return this.regNumber;
    }
    @Override
    public String toString() {
        return "Studentul: " + this.name + ", cu reg number: " + this.regNumber + "\n";
    }
    @Override
     public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Student student = (Student) obj;
        return this.regNumber.equals(student.regNumber);
    }
    @Override
    public int hashCode(){
        return this.regNumber.hashCode();
    }
}

class Project {
    private String name;
    private Types type;

    public Project(String name, Types type) {
        this.name = name;
        this.type = type;
    }
    public Project() {
        this.name = null;
        this.type = null;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public void setType(Types type) {
        this.type = type;
    }
    public Types getType() {
        return this.type;
    }
    @Override
    public String toString() {
        return "Proiectul: " + this.name + ", cu type-ul: " + this.type + "\n";
    }
    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if(obj == null || obj.getClass() != this.getClass()) return false;
        Project project = (Project) obj;
        return this.name.equals(project.name) && this.type.equals(project.type);
    }
    @Override
    public int hashCode(){
        return this.name.hashCode() + this.type.hashCode();
    }
}
class Problem
{
    private Student[] studentList;
    private Teacher[] teacherList;
    private int studentCount;
    private int teacherCount;

    public Problem(int maxStudents, int maxTeachers)
    {
        studentList = new Student[maxStudents];
        teacherList = new Teacher[maxTeachers];
        studentCount = 0;
        teacherCount = 0;
    }

    public void addStudent(Student student)
    {
        for(int i=0;i < studentCount;i++)
            if(studentList[i].equals(student)) {
                System.out.println("Studentul exista deja");
                return;
            }
        studentList[studentCount++] = student;
    }
    public void addTeacher(Teacher teacher)
    {
        for(int i=0; i < teacherCount;i++)
            if(teacherList[i].equals(teacher)) {
                System.out.println("Profesorul exista deja");
                return;
            }
        teacherList[teacherCount++] = teacher;
    }

    public Person[] getPersons()
    {
        Person[] persons = new Person[studentCount + teacherCount];
        int count = 0;
        for(int i=0; i < studentCount;i++)
            persons[count++] = studentList[i];
        for(int i=0; i < teacherCount;i++)
            persons[count++] = teacherList[i];
        return persons;
    }

    public void allocateProjects()
    {
        Project[] allProjects = new Project[1024];
        int count = 0;
        for(int i=0; i < teacherList.length;i++)
            if(teacherList[i] != null) {
                for (int j = 0; j < teacherList[i].projectList.length; j++) {
                    if (teacherList[i].projectList[j] != null) {
                        boolean exists = false;
                        for (int k = 0; k < count; k++) {
                            if (allProjects[k].equals(teacherList[i].projectList[j])) {
                                exists = true;
                                break;
                            }
                        }
                        if (!exists)
                            allProjects[count++] = teacherList[i].projectList[j];
                    }
                }
            }
            
        if(count < studentCount) {
            System.out.println("Nu sunt destule proiecte pentru studenti");
            return;
        }
        for(int i=0; i < studentCount; i++)
            System.out.println("Studentul: " + studentList[i] + " primeste proiectul: " + allProjects[i] + "\n");
    }
}
public class Main {
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