import java.util.Date;

/**
 * Clasa abstracta care defineste o persoana(student sau profesor)
 */
abstract class Person
{
    String name;
    Date dob;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
