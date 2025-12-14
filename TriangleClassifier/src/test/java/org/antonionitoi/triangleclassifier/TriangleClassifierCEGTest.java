package org.antonionitoi.triangleclassifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleClassifierCEGTest {
    private TriangleClassifier triangleClassifier = new TriangleClassifier();

    private void assertClassification(int a, int b, int c, String expected) {
        String result = triangleClassifier.classify(a, b, c);

        assertEquals(expected, result,
                "Clasificare greșită (" + result + " în loc de " + expected + ") pentru valorile: a = " + a + ", b = " + b + ", c = " + c);
    }

    @Test
    @DisplayName("CEG1 – C1 fals (a ≤ 0) => INVALID")
    void cegInvalidNonPositiveA() {
        assertClassification(0, 5, 5, "INVALID");
    }

    @Test
    @DisplayName("CEG2 – C2 fals (b ≤ 0) => INVALID")
    void cegInvalidNonPositiveB() {
        assertClassification(5, -1, 5, "INVALID");
    }

    @Test
    @DisplayName("CEG3 – C3 fals (c ≤ 0) => INVALID")
    void cegInvalidNonPositiveC() {
        assertClassification(5, 5, 0, "INVALID");
    }

    @Test
    @DisplayName("CEG4 – C4 fals (a + b ≤ c) => INVALID")
    void cegInvalidTriangleInequalityABvsC() {
        assertClassification(2, 3, 5, "INVALID");
    }

    @Test
    @DisplayName("CEG5 – C5 fals (a + c ≤ b) => INVALID")
    void cegInvalidTriangleInequalityACvsB() {
        assertClassification(2, 7, 3, "INVALID");
    }

    @Test
    @DisplayName("CEG6 – C6 fals (b + c ≤ a) => INVALID")
    void cegInvalidTriangleInequalityBCvsA() {
        assertClassification(8, 3, 4, "INVALID");
    }

    @Test
    @DisplayName("CEG7 – a = b = c => EQUILATERAL")
    void cegEquilateralAllEqual() {
        assertClassification(5, 5, 5, "EQUILATERAL");
    }

    @Test
    @DisplayName("CEG8 – Doar a = b => ISOSCELES")
    void cegIsoscelesAB() {
        assertClassification(5, 5, 3, "ISOSCELES");
    }

    @Test
    @DisplayName("CEG9 – Doar b = c => ISOSCELES")
    void cegIsoscelesBC() {
        assertClassification(4, 6, 6, "ISOSCELES");
    }

    @Test
    @DisplayName("CEG10 – Doar a = c => ISOSCELES")
    void cegIsoscelesAC() {
        assertClassification(6, 5, 6, "ISOSCELES");
    }

    @Test
    @DisplayName("CEG11 – a != b != c => SCALENE")
    void cegScaleneAllDistinct() {
        assertClassification(4, 5, 6, "SCALENE");
    }
}

