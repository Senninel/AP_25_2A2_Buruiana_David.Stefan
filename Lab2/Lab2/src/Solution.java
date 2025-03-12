
class Solution
{
    private Problem problem;
    Student[] studentsOrdered;
    Project[] projectsOrdered;
    int length = 0;

    public Solution(Problem problem)
    {
        this.problem = problem;
        studentsOrdered = new Student[problem.getStudentCount()];
    }
    public void AllocateStudents()
    {
        Project[] allProjects = new Project[1024];
        Teacher[] teachers = new Teacher[problem.getTeacherCount()];
        teachers = problem.getTeacherList();
        Student[] students = new Student[1024];
        students = problem.getStudentList();

        int count = 0;
        for(int i=0;i < teachers.length;i++) {
            if (teachers[i] != null) {
                for(int j=0;j < teachers[i].projectList.length;j++) {
                    if(teachers[i].projectList[j] != null) {
                        boolean exists = false;
                        for(int k = 0; k < count; k++){
                            if(allProjects[k].equals(teachers[i].projectList[j])) {
                                exists = true;
                                break;
                            }
                        }
                        if(!exists) {
                            allProjects[count++] = teachers[i].projectList[j];
                        }
                    }
                }
            }
        }
        projectsOrdered = new Project[count];
        for(int i=0; i < students.length;i++) {
            if(students[i] != null) {
                for (int j = i + 1; j < students.length; j++) {
                    if(students[j] != null) {
                        if (students[i].getLength() > students[j].getLength()) {
                            Student student = students[i];
                            students[i] = students[j];
                            students[j] = student;
                        }
                    }
                }
            }
        }
        boolean[] usedProject = new boolean[allProjects.length];
   for(int i = 0; i < students.length;i++){
       if(students[i] != null) {
           boolean Found = false;
           for(int j=0; j < allProjects.length;j++){
               if(allProjects[j] != null){
                   Project[] prefferedProjects = new Project[15];
                   prefferedProjects = students[i].getPrefferedProjects();
                   for(int k=0 ; k <  prefferedProjects.length;k++){
                       if(prefferedProjects[k] != null){
                           if(prefferedProjects[k].equals(allProjects[j]) && usedProject[j] == false){
                               usedProject[j] = true;
                               projectsOrdered[length++] = prefferedProjects[k];
                               Found = true;
                           }
                       }
                   }
               }
           }
           if(!Found){
               System.out.println("Nu am gasit o ordonare a proiectelor!");
               return;
           }
       }
   }
   studentsOrdered = students;
   for(int i=0; i < studentsOrdered.length;i++) {
       if(studentsOrdered[i] != null){
           System.out.println(studentsOrdered[i].toString() + "primeste: " + projectsOrdered[i].toString());
       }
   }
   }
}