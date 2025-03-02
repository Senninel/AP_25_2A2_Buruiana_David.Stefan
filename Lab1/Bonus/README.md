1. Am generat random n ce reprezinta numarul de noduri ale grafului(maxim 14) si am 
creat matricea random cum am facut si in tema(rdm genereaza 0, caz in care nu este
muchie intre nodurile i si j, si 1, unde se va crea muchie intre ele).
2. Am citit de la tastatura k.
3. Pentru a verifica daca exista o clica de lungime minim k am folosit functia Clique
in care am folosit backtracking in care am generat permutari de lungime minim k in care 
fiecare element din stiva reprezinta numarul nodului din graf. In functia Valid m am 
asigurat ca generez corect permutari de la 1 la n, iar in functia VerificareClique 
stiu sigur ca am stiva de lungime minim k si am verificat daca reprezinta un graf
complet stiva in acel moment, caz in care daca este graf complet returnez lungimea
clicai si opresc backtracking-ul pentru ca am gasit macar o clica de lungmime minim k.
4. Pentru a verifica daca exista o multime stabila de lungime minim k ne putem folosi de
functia facuta anterior Clique. Deoarece o multime stabila inseamna ca nodurile nu sunt
adiacente intre ele, prin inversarea matricei(facand complementara sa) vom crea un o 
clica completa de lungime egala cu multimea stabila. Astfel am facut complementara 
matricei si am verificat daca gasesc o clica de lungime minim k.