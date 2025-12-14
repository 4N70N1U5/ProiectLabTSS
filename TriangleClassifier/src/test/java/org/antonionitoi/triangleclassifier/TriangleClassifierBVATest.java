package org.antonionitoi.triangleclassifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleClassifierBVATest {
    private TriangleClassifier triangleClassifier = new TriangleClassifier();

    private void assertClassification(int a, int b, int c, String expected) {
        String result = triangleClassifier.classify(a, b, c);

        assertEquals(expected, result,
                "Clasificare greșită (" + result + " în loc de " + expected + ") pentru valorile: a = " + a + ", b = " + b + ", c = " + c);
    }

    @Test
    @DisplayName("BVA1 – Limita pozitivitații (−1 / 0 / 1)")
    void bvaLowerBoundPositivity() {
        assertClassification(-1, 2, 3, "INVALID");
        assertClassification(0, 2, 3, "INVALID");
        assertClassification(1, 1, 1, "EQUILATERAL");
    }

    @Test
    @DisplayName("BVA2 – Inegalitatea triunghiului pentru a = b = 5")
    void bvaTriangleInequalityEdge() {
        assertClassification(5, 5, 9, "ISOSCELES");
        assertClassification(5, 5, 10, "INVALID");
        assertClassification(5, 5, 11, "INVALID");
    }

    @Test
    @DisplayName("BVA3 – Limita superioară a tipului int")
    void bvaUpperBoundValues() {
        int max = Integer.MAX_VALUE;
        assertClassification(max - 1, max - 1, max - 1, "EQUILATERAL");
        assertClassification(max, max, max, "EQUILATERAL");
    }
}
