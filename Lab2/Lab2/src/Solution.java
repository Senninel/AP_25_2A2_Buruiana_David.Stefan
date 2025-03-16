
class Solution
{
    private Problem problem;
    Student[] studentsOrdered;
    Project[] projectsOrdered;
    int length = 0;
    int[] st;
    public Solution(Problem problem)
    {
        this.problem = problem;
        studentsOrdered = new Student[problem.getStudentCount()];
    }
    private boolean foundProjectTeacherList(Project p){
        Teacher[] teacherList = problem.getTeacherList();
        for(int i = 0; i < teacherList.length; i++){
            if(teacherList[i] != null){
                Project projectList[] = teacherList[i].getProjectList();
                for(int k=0;k<projectList.length;k++){
                    if(projectList[k] != null){
                        if(projectList[k].equals(p)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public int Valid(int top)
    {
        for(int i=1;i<top;i++)
            if(st[i] == st[top]) return 0;
        return 1;
    }
    public int verificareSubset(int top)
    {
        Project[] allProjects = new Project[1024];
        int len = 0;
        Student[] students = new Student[1024];
        Student[] studentsList = problem.getStudentList();
        int studentLen = 0;
        for(int i=1;i<=top;i++)
            students[studentLen++] = studentsList[st[i] - 1];
        for(int i=0;i<students.length;i++)
            if(students[i] != null){
                Project prefferedProjects[] = students[i].getPrefferedProjects();
                for(int j=0;j < students[i].getLength();j++){
                    boolean found = false;
                    for(int k=0; k<allProjects.length;k++){
                        if(allProjects[k] != null){
                            if(allProjects[k].equals(prefferedProjects[j])){
                                found = true;
                            }
                        }
                    }
                    if(found == false && foundProjectTeacherList(prefferedProjects[j])) allProjects[len++] = prefferedProjects[j];
                }
            }
        if(len < studentLen) return 1;
        return 0;
    }
    public void verifyHall()
    {
        int top, cand;
        int gasit = 0;
        top = 0;
        st = new int[problem.getStudentCount() + 1];
        st[++top] = 0;
        while(top > 0 && gasit == 0)
        {
            cand = 0;
            while(cand == 0 && st[top] < problem.getStudentCount())
            {
                st[top]++;
                cand = Valid(top);
            }
            if(cand == 0) top--;
            else if(top > 0) gasit = verificareSubset(top);
            else st[++top] = 0;
        }
        if(gasit == 1)
            System.out.println("Nu se poate aloca");
        else
            System.out.println("Se poate aloca");
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