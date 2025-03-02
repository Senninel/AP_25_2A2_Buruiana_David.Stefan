1. Am luat primele 2 argumente pentru a avea valori concrete pentru n si k.
2. Am verificat daca n este de cel putin 2 ori mai mare decat k. 
	2.1. In cazul in care nu este, voi crea o matrice random fara proprietatile date si pentru a face generarea matricei
 	random am folosit variabila rdm care poate avea valori de 0 sau 1 si in cazul in care este 1 atunci 
	muchiile a[i][j] si a[j][i] vor exista in graf. 
	2.2. In cazul in care este, voi genera random k noduri pentru a face o clica de lungime minim k si inca k noduri 
	ce nu au fost folosite care vor face multimea stabila de lungime minim k. Dupa am generat restul de muchii care
	pot fi adaugate in graf.
3. Pentru a reprezenta matricea am create un String in care am trecut valorile matricei.
4. Pentru a numara muchiile am facut for-ul doar pe elementele deasupra diagonalei principale deoarece este graf 
neorientat si partea de deasupra diagonalei principale sunt identice cu cele de sub diagonala principala si se numara
doar o singura data.
5. Pentru a gasit gradul maxim, minim si suma gradelor se face primul for care reprezinta nodul curent in care sunt si al doilea for
pentru a vedea nodurile adiacente cu acesta dand astfel gradul nodului curent.