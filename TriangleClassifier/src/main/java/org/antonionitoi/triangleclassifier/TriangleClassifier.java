package org.antonionitoi.triangleclassifier;

/**
 * TriangleClassifier
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
public class TriangleClassifier {

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
        if (a == b && b == c) {
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
