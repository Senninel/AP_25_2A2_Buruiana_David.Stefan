import java.util.Date;

/**
 * Clasa student care extinde clasa person
 */
class Student  extends Person
{
    private Long regNumber;

    /**
     * Construieste un obiect de tip student
     * @param name Numele studentului
     * @param regNumber Numarul de inregistrare unic
     * @param dob Data de nastere a studentului
     */
    public Student(String name, Long regNumber, Date dob) {
        this.name = name;
        this.regNumber = regNumber;
        this.dob = dob;
    }



    /**
     * Seteaza/schimba numele unui student
     * @param name Numele nou
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Primesti numele studentului
     * @return Nume student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Seteaza/schimba regNumber la student
     * @param regNumber Noul regNumber
     */
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