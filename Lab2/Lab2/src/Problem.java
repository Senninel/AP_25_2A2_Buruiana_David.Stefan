/**
 * Clasa Problem creeaza o lista de studenti si profesori in care se vor aloca proiecte din lista disponibila
 * a profesorilor de proiecte fiecarui student.
 */
class Problem
{
    private Student[] studentList;
    private Teacher[] teacherList;
    private int studentCount;
    private int teacherCount;

    /**
     * Constructor care aloca numarul maxim de studenti si profesori la problema.
     * @param maxStudents Numarul maxim de studenti
     * @param maxTeachers Numarul maxim de profesori
     */
    public Problem(int maxStudents, int maxTeachers)
    {
        studentList = new Student[maxStudents];
        teacherList = new Teacher[maxTeachers];
        studentCount = 0;
        teacherCount = 0;
    }

    public Teacher[] getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(Teacher[] teacherList) {
        this.teacherList = teacherList;
    }

    public Student[] getStudentList() {
        return studentList;
    }

    public void setStudentList(Student[] studentList) {
        this.studentList = studentList;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(int teacherCount) {
        this.teacherCount = teacherCount;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    /**
     * Adauga un student nou la problema
     * @param student
     */
    public void addStudent(Student student)
    {
        for(int i=0;i < studentCount;i++)
            if(studentList[i].equals(student)) {
                System.out.println("Studentul exista deja");
                return;
            }
        studentList[studentCount++] = student;
    }

    /**
     * Adauga un profesor nou la problema
     * @param teacher
     */
    public void addTeacher(Teacher teacher)
    {
        for(int i=0; i < teacherCount;i++)
            if(teacherList[i].equals(teacher)) {
                System.out.println("Profesorul exista deja");
                return;
            }
        teacherList[teacherCount++] = teacher;
    }

    /**
     * Returneaza toate persoanele din toata problema(studenti + profesori)
     * @return Un obiect de tip person cu toate persoanele din problema
     */
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

}

