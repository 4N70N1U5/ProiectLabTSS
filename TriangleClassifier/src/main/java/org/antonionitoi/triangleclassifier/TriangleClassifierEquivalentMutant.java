package org.antonionitoi.triangleclassifier;

/**
 * TriangleClassifierEquivalentMutant
 *
 * Clasifică un triunghi pe baza lungimilor laturilor sale.
 *
 * Returnează una dintre valorile:
 *  - "INVALID"
 *  - "EQUILATERAL"
 *  - "ISOSCELES"
 *  - "SCALENE"
 *
 * Reguli:
 *  - Toate laturile trebuie să fie strict pozitive.
 *  - Inegalitățile triunghiului trebuie respectate.
 */
public class TriangleClassifierEquivalentMutant {

    /**
     * Clasifică triunghiul definit de laturile a, b și c.
     *
     * @param a lungimea primei laturi
     * @param b lungimea celei de-a doua laturi
     * @param c lungimea celei de-a treia laturi
     * @return tipul triunghiului
     */
    public static String classify(int a, int b, int c) {

        // ===== Decizia D1: laturi pozitive =====
        if (a <= 0 || b <= 0 || c <= 0) {
            return "INVALID";
        }

        // Convertim la long pentru a evita overflow la adunare
        long la = a;
        long lb = b;
        long lc = c;

        // ===== Decizia D2: inegalitățile triunghiului =====
        if (la + lb <= lc || la + lc <= lb || lb + lc <= la) {
            return "INVALID";
        }

        // ===== Decizia D3: toate laturile egale =====
        // Modificare mutantă: schimbăm condiția pentru a crea un mutant echivalent
        // Original: if (a == b && b == c)
        // Mutant: if (a == b & b == c)
        // Am aplicat o singură mutație de tip Logical Operator Replacement (LOR).
        // Mutația constă în înlocuirea operatorului && cu & în decizia care verifică
        // dacă triunghiul este echilateral. a == b și b == c sunt expresii booleene
        // fără efecte secundare. În Java, pentru operanzi booleeni, operatorii && și &
        // au același rezultat logic, diferența fiind doar la nivel de strategie de evaluare
        // (short‑circuit vs evaluare completă). Deoarece evaluarea completă nu produce efecte
        // secundare și nu poate genera overflow sau excepții, rezultatul expresiei este identic
        // pentru orice combinație de valori (a, b, c). Prin urmare, programul mutant este
        // comportamental identic cu programul original și reprezintă un mutant echivalent de
        // ordinul 1.
        if (a == b & b == c) {
            return "EQUILATERAL";
        }

        // ===== Decizia D4: două laturi egale =====
        if (a == b || a == c || b == c) {
            return "ISOSCELES";
        }

        // ===== Decizia D5: toate laturile diferite =====
        return "SCALENE";
    }
}
