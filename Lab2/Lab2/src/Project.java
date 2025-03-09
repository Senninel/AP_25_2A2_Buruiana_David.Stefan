
/**
 * Clasa proiect in care se stocheaza numele unui proiect si tipul sau
 */
class Project {
    private String name;
    private Types type;

    /**
     * Constructorul proiectului cu parametri
     * @param name Numele proiectului
     * @param type Tipul proiectului
     */
    public Project(String name, Types type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Constructor fara parametri
     */
    public Project() {
        this.name = null;
        this.type = null;
    }

    /**
     * Setezi/schimbi nume proiect
     * @param name Nume nou proiect
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Primesti numele proiectului
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setezi/schimbi tipul proiectului
     * @param type
     */
    public void setType(Types type) {
        this.type = type;
    }

    /**
     * Primeti tipul proiectului
     * @return
     */
    public Types getType() {
        return this.type;
    }

    /**
     * Returneaza un string cu numele proiectului si tipul sau.
     * @return
     */
    @Override
    public String toString() {
        return "Proiectul: " + this.name + ", cu type-ul: " + this.type + "\n";
    }

    /**
     * Compara 2 proiecte fiind diferentiate prin nume si tip
     * @param obj Al doilea proiect
     * @return
     */
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