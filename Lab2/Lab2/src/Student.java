import java.util.Date;

/**
 * Clasa student care extinde clasa person
 */
class Student  extends Person
{
    private Long regNumber;
    private Project[]  prefferedProjects;
    int length = 0;
    /**
     * Construieste un obiect de tip student
     * @param name Numele studentului
     * @param regNumber Numarul de inregistrare unic
     * @param dob Data de nastere a studentului
     */
    public Student(String name, Long regNumber, Date dob, Project project) {
        this.name = name;
        this.regNumber = regNumber;
        this.dob = dob;
        prefferedProjects = new Project[10];
        prefferedProjects[length++] = project;
    }


    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Project[] getPrefferedProjects() {
        return prefferedProjects;
    }

    public void setPrefferedProjects(Project[] prefferedProjects) {
        this.prefferedProjects = prefferedProjects;
    }

    public void setRegNumber(Long regNumber) {
        this.regNumber = regNumber;
    }

    /**
     * Primesti regNumberul studentului
     * @return regNumber student
     */
    public Long getRegNumber() {
        return this.regNumber;
    }

    /**
     * Primesti numele studentului impreuna cu regNumber sau
     * @return Nume student + regNumber
     */

    public void addPrefferedProject(Project project)
    {
        prefferedProjects[length++] = project;
    }
    @Override
    public String toString() {
        return "Studentul: " + this.name + ", cu reg number: " + this.regNumber + "\n";
    }

    /**
     * Compara 2 studenti fiind diferentiati prin regNumber
     * @param obj
     * @return
     */
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