# Proiect Laborator TSS

### Testarea unui clasificator de triunghiuri

### Nițoi Antonio, Grupa 506 (Master IS)

## Specificația programului TriangleClassifier

### Scop
`TriangleClassifier` primește lungimile întregi ale celor trei laturi ale unui triunghi și întoarce tipul triunghiului folosind metoda publică `classify(int a, int b, int c)`.

### Domeniul de intrare
Valorile a, b și c, lungimile celor trei laturi ale triunghiului, numere întregi (Java `int`).

### Reguli de validare
1. **Pozitive:** toate laturile trebuie să fie strict pozitive (`> 0`).
2. **Inegalitatea triunghiului:** fiecare pereche de laturi trebuie să aibă suma strict mai mare decât a treia latură (`a + b > c`).
Dacă oricare dintre regulile de mai sus e încălcată, metoda returnează `"INVALID"`.

### Clasificarea rezultatelor
Ordinea deciziilor este deterministă:
1. `"INVALID"` – dacă regulile de validare eșuează.
2. `"EQUILATERAL"` – dacă toate cele trei laturi sunt egale.
3. `"ISOSCELES"` – dacă exact două laturi sunt egale.
4. `"SCALENE"` – dacă toate laturile sunt diferite și regulile de validare sunt satisfăcute.

## Rezolvarea cerințelor

### Cerința 1: Generarea datelor de test...

#### a) ...folosind Equivalence Partitioning (EP)

##### Principiu

Spațiul de intrare este împărțit în clase de echivalență, astfel încât:
- orice valoare dintr-o clasă produce același comportament
- este suficient un test reprezentativ per clasă

##### Implementare

Partiționări identificate:

| ID partiționare | Condiție logică                                  | Tip triunghi | Exemplu reprezentativ | Rezultat așteptat |
|-----------------|--------------------------------------------------|--------------|-----------------------|-------------------|
| EP1             | O latură egală cu 0                              | INVALID      | (0,5,7)               | "INVALID"         |
| EP2             | O latură negativă                                | INVALID      | (3,-1,4)              | "INVALID"         |
| EP3             | Toate pozitive, dar suma a două laturi ≤ a treia | INVALID      | (2,3,10)              | "INVALID"         |
| EP4             | a = b = c > 0 și respectă inegalitățile          | EQUILATERAL  | (5,5,5)               | "EQUILATERAL"     |
| EP5             | Exact două laturi egale, triunghi valid          | ISOSCELES    | (5,5,7)               | "ISOSCELES"       |
| EP6             | Toate laturile diferite și valide                | SCALENE      | (4,5,6)               | "SCALENE"         |

Testele corespunzătoare se găsesc în `TriangleClassifier/src/test/java/org/antonionitoi/triangleclassifier/TriangleClassifierEPTest.java` și includ câte o metodă JUnit pentru fiecare clasă de echivalență.

Rularea testelor:
```bash
cd TriangleClassifier
mvn test -Dtest=TriangleClassifierEPTest
```

#### b) ...folosind Boundary Value Analysis (BVA)

##### Principiu

Erorile apar frecvent la limite:
- valori minime/maxime
- tranziția valid ↔ invalid

Se testează:
- limita
- imediat sub limită
- imediat peste limită

##### Implementare

Valori limită pentru pozitivitatea laturilor:

Lungimea unei laturi trebuie să fie un număr întreg strict pozitiv. Limita inferioară a domeniului este valoarea 1.

| Valoare testată | Poziție față de limită | Rezultat așteptat |
|-----------------|------------------------|-------------------|
| -1              | imediat sub limită     | INVALID           |
| 0               | la limită              | INVALID           |
| 1               | imediat peste limită   | VALID             |

Valori limită pentru inegalitatea triunghiului:

Pentru formarea unui triunghi valid trebuie să fie satisfăcută inegalitatea triunghiului: `a + b > c`.

Dacă luăm valorile `a = b = 5`, limita pentru `c` este dată de formula `a + b = c`, deci limita va fi `c = 10`.

| Valoare testată pentru `c` | Poziție față de limită | Rezultat așteptat |
|----------------------------|------------------------|-------------------|
| 9                          | imediat sub limită     | VALID             |
| 10                         | la limită              | INVALID           |
| 11                         | imediat peste limită   | INVALID           |

Valori limită pentru tipul de date `int`:

Tipul de date int are o limită superioară finită, Integer.MAX_VALUE. Nu există o valoare reprezentabilă imediat peste această limită.

| Valoare testată       | Poziție față de limită | Rezultat așteptat |
|-----------------------|------------------------|-------------------|
| Integer.MAX_VALUE − 1 | imediat sub limită     | VALID             |
| Integer.MAX_VALUE     | la limită              | VALID             |

Testele corespunzătoare se găsesc în `TriangleClassifier/src/test/java/org/antonionitoi/triangleclassifier/TriangleClassifierBVATest.java` și verifică explicit fiecare limită identificată mai sus.

Rularea testelor BVA:
```bash
cd TriangleClassifier
mvn test -Dtest=TriangleClassifierBVATest
```

#### c) ...folosind Cause-Effect Graphing (CEG)

##### Principiu

Se determină cauzele (condițiile de intrare) și efectele (rezultatele așteptate), apoi se construiește un graf care le leagă.

##### Implementare

Cauze identificate:

| ID Cauză | Condiție logică |
|----------|-----------------|
| C1       | a > 0           |
| C2       | b > 0           |
| C3       | c > 0           |
| C4       | a + b > c       |
| C5       | a + c > b       |
| C6       | b + c > a       |
| C7       | a == b          |
| C8       | b == c          |
| C9       | a == c          |

Efecte identificate:

| ID Efect | Rezultat    |
|----------|-------------|
| E1       | INVALID     |
| E2       | EQUILATERAL |
| E3       | ISOSCELES   |
| E4       | SCALENE     |

Graful cauză-efect:

| C1 | C2 | C3 | C4 | C5 | C6 | C7 | C8 | C9 | E1 | E2 | E3 | E4 |
|----|----|----|----|----|----|----|----|----|----|----|----|----|
| F  | -  | -  | -  | -  | -  | -  | -  | -  | X  |    |    |    |
| -  | F  | -  | -  | -  | -  | -  | -  | -  | X  |    |    |    |
| -  | -  | F  | -  | -  | -  | -  | -  | -  | X  |    |    |    |
| T  | T  | T  | F  | -  | -  | -  | -  | -  | X  |    |    |    |
| T  | T  | T  | T  | F  | -  | -  | -  | -  | X  |    |    |    |
| T  | T  | T  | T  | T  | F  | -  | -  | -  | X  |    |    |    |
| T  | T  | T  | T  | T  | T  | T  | T  | T  |    | X  |    |    |
| T  | T  | T  | T  | T  | T  | T  | F  | F  |    |    | X  |    |
| T  | T  | T  | T  | T  | T  | F  | T  | F  |    |    | X  |    |
| T  | T  | T  | T  | T  | T  | F  | F  | T  |    |    | X  |    |
| T  | T  | T  | T  | T  | T  | F  | F  | F  |    |    |    | X  |

Testele corespunzătoare se găsesc în `TriangleClassifier/src/test/java/org/antonionitoi/triangleclassifier/TriangleClassifierCEGTest.java` și includ câte o metodă JUnit pentru fiecare combinație relevantă din tabelul de mai sus.

Rularea testelor CEG:
```bash
cd TriangleClassifier
mvn test -Dtest=TriangleClassifierCEGTest
```

### Cerința 2: Stabilirea nivelului de acoperire a testelor de la Cerința 1

Pentru fiecare set de teste (EP, BVA și CEG) s-a rulat `mvn clean test -Dtest=<TestClass>` cu pluginul JaCoCo. Rapoartele generate au fost mutate din `target/site/jacoco` în folderele `TriangleClassifier/coverage/ep`, `TriangleClassifier/coverage/bva` și `TriangleClassifier/coverage/ceg` pentru a nu fi ignorate de `.gitignore` și pentru a putea fi comparate.

#### Rezultate Acoperire (JaCoCo)

| Set Teste                         | Instructions Covered | Branches Covered | Lines Covered | Complexity Covered |
|-----------------------------------|----------------------|------------------|---------------|--------------------|
| **EP** (Equivalence Partitioning) | 100% (61/61)         | 77% (17/22)      | 100% (13/13)  | 61% (8/13)         |
| **BVA** (Boundary Value Analysis) | 86% (53/61)          | 54% (12/22)      | 92% (12/13)   | 38% (5/13)         |
| **CEG** (Cause-Effect Graphing)   | 100% (61/61)         | 100% (22/22)     | 100% (13/13)  | 100% (13/13)       |

#### Comentarii și Compararea Rezultatelor

1.  **Equivalence Partitioning (EP)**:
    - A obținut o acoperire bună, dar nu completă a ramurilor (77%).
    - Deși a acoperit toate tipurile de rezultate (INVALID, EQUILATERAL, ISOSCELES, SCALENE), nu a explorat toate combinațiile de condiții din instrucțiunile `if` compuse (de exemplu, alegând câte un singur exemplu reprezentativ per clasă de echivalență, a testat doar o singură combinație de laturi pentru care inegalitatea triunghiului eșua).

2.  **Boundary Value Analysis (BVA)**:
    - A obținut cea mai mică acoperire (54% branches).
    - Motivul este că setul de teste BVA s-a concentrat strict pe limitele identificate (0, 1, MAX_INT, limitele inegalității). Aceste limite au tins să producă rezultate `INVALID`, `EQUILATERAL` sau `ISOSCELES`, dar au omis cazul unui triunghi `SCALENE` valid (care se află "în interiorul" domeniului, nu la granițe). Acest lucru demonstrează că BVA singur poate fi insuficient dacă nu este combinat cu alte tehnici pentru a acoperi "interiorul" claselor de echivalență.

3.  **Cause-Effect Graphing (CEG)**:
    - A obținut acoperirea maximă posibilă pentru logica metodei (100% branches).
    - Metoda a forțat analiza tuturor combinațiilor de cauze (condiții), asigurând că fiecare parte a expresiilor booleene complexe a fost evaluată. De exemplu, a generat teste specifice pentru fiecare pereche de laturi care încalcă inegalitatea triunghiului și pentru fiecare pereche de laturi egale.

**Concluzie:** Pentru acest program, **Cause-Effect Graphing** s-a dovedit a fi cea mai robustă tehnică, generând un set de teste care a exercitat complet logica decizională a programului.
