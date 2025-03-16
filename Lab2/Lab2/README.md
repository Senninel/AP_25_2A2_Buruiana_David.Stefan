1. Am creat clasa abstracta Person din care derivez clasele Student si Teacher
2. Clasa Student are pe langa membri clasei Person regNumber si un vector de proiecte(din clasa
de proiecte) ce reprezinta proiectele sale preferate si un length care e lungimea vectorului, 
setere si getere pentru toti membri, o functie de a adauga proiecte noi preferate si override la
toString, equls is hashCode.
3. Clasa Profesor ce are pe langa membri clasei person si vectorul de proiecte la care este 
alocat acesta. Pe langa setere si getere exista si metoda addProject ce adauga in lista de 
proiecte existente unul nou si override exact ca la clasa Student.
4. Clasa Project reprezinta proiect cu un nume si un tip din enum(practic sau teoretic). Contine
constructori(fara argmunete si cu argumente), setere, getere si override ca la clasele de mai sus.
5. Clasa Problem are 4 membri: un vector de studenti si lungimea acestuia si un vector de 
profesori si lungimea acestuia. Pe langa constructuri, setere si getere mai are 2 metode de 
adaugare de profesori sau studenti in lista problemei si o metoda de tip Person ce returneaza
un vector cu persoanele implicate in problema.
6. Clasa Solution are 4 membri: problema ce trebuie rezolvata(de tip Problem), doi vectori
ce ordoneaza studentii si proiectele(primul student -> primul proiect) si lungimea vectorului.
 Metoda ce rezolva problema(AllocateStudents()) e o problema greedy implementata astfel:
am facut un vector in care am trecut proiectele de la toti profesorii fara sa se repete, 
unul de studenti unde sunt ordonati studentii in functie de preferinte lor(cei cu nr cat mai 
mic de proiecte preferate vor avea prioriate). Dupa sortare se va trece prin lista de studenti
si se vor aloca proiecte din cele preferate care nu sunt luate deja(vectorul usedProject verifica
asta). Si la final se afiseaza lista cu student + proiect repartizat.