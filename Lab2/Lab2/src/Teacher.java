import java.util.Date;

/**
 * Clasa profesor care extinde clasa persoana.
 */
class Teacher extends Person
{
    Project[] projectList = new Project[105];

    /**
     * Constructor pentru a initaliza un obiect de tip profesor cu nume, data de nastere si proiect.
     * @param name Numele profesorului
     * @param dob Data de nastere a profesorului
     * @param project Proiectul initial a profesorului
     */
    public Teacher(String name, Date dob, Project project)
    {
        this.name = name;
        this.dob = dob;
        this.projectList = new Project[105];
        projectList[0] = project;
    }

    /**
     * Adauga un proiect la lista profesorului
     * @param project Adauga proiectul project in lista
     */
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

    /**
     *
     * @return la nume profesor si lista de proiecte
     */
    @Override
        public String toString(){
         StringBuilder s = new StringBuilder();
         for(int i=0;i<projectList.length;i++)
             if(projectList[i]!=null)
                 s.append(projectList[i].toString()+"\n");
         return "Profesorul " + this.name + " are urmatoarele proiecte " + s;
    }

    /**
     *
     * @param obj
     * @return Compara 2 obiecte de tip profesor fiind unice prin nume si lista de proiecte
     */
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