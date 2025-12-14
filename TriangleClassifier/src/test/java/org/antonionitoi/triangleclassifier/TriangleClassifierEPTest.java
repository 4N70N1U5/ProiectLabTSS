package org.antonionitoi.triangleclassifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleClassifierEPTest {
    private TriangleClassifier triangleClassifier = new TriangleClassifier();

    private void assertClassification(int a, int b, int c, String expected) {
        String result = triangleClassifier.classify(a, b, c);

        assertEquals(expected, result,
                "Clasificare greșită (" + result + " în loc de " + expected + ") pentru valorile: a = " + a + ", b = " + b + ", c = " + c);
    }

    @Test
    @DisplayName("EP1 – Latură egală cu 0 => INVALID")
    void epInvalidZeroSide() {
        assertClassification(0, 5, 7, "INVALID");
    }

    @Test
    @DisplayName("EP2 – Latură negativă => INVALID")
    void epInvalidNegativeSide() {
        assertClassification(3, -1, 4, "INVALID");
    }

    @Test
    @DisplayName("EP3 – Încălcare inegalitatea triunghiului => INVALID")
    void epInvalidTriangleInequality() {
        assertClassification(2, 3, 10, "INVALID");
    }

    @Test
    @DisplayName("EP4 – Triunghi echilateral valid")
    void epValidEquilateral() {
        assertClassification(5, 5, 5, "EQUILATERAL");
    }

    @Test
    @DisplayName("EP5 – Triunghi isoscel valid")
    void epValidIsosceles() {
        assertClassification(5, 5, 7, "ISOSCELES");
    }

    @Test
    @DisplayName("EP6 – Triunghi scalen valid")
    void epValidScalene() {
        assertClassification(4, 5, 6, "SCALENE");
    }
}

