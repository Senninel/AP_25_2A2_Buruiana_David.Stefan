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

    /**
     * Aloca fiecarui student un proiect din lista de proiecte disponibila de la toti profesorii
     * adaugati in problema, proiect diferit fata de ceilalti studenti.
     */
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

