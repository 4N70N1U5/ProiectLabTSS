package org.antonionitoi.triangleclassifier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TriangleClassifierMCDCTest {
    private TriangleClassifier triangleClassifier = new TriangleClassifier();

    private void assertClassification(int a, int b, int c, String expected) {
        String result = triangleClassifier.classify(a, b, c);

        assertEquals(expected, result,
                "Clasificare greșită (" + result + " în loc de " + expected + ") pentru valorile: a = " + a + ", b = " + b + ", c = " + c);
    }

    @Nested
    @DisplayName("MC/DC pentru D1: a <= 0 || b <= 0 || c <= 0")
    class Decision1Tests {
        @Test
        void d1AllConditionsFalse() {
            assertClassification(3, 4, 5, "SCALENE");
        }

        @Test
        void d1ConditionC1True() {
            assertClassification(-3, 4, 5, "INVALID");
        }

        @Test
        void d1ConditionC2True() {
            assertClassification(3, -4, 5, "INVALID");
        }

        @Test
        void d1ConditionC3True() {
            assertClassification(3, 4, -5, "INVALID");
        }
    }

    @Nested
    @DisplayName("MC/DC pentru D2: a + b <= c || a + c <= b || b + c <= a")
    class Decision2Tests {
        @Test
        void d2AllConditionsFalse() {
            assertClassification(3, 4, 5, "SCALENE");
        }

        @Test
        void d2ConditionC4True() {
            assertClassification(3, 4, 10, "INVALID");
        }

        @Test
        void d2ConditionC5True() {
            assertClassification(3, 10, 5, "INVALID");
        }

        @Test
        void d2ConditionC6True() {
            assertClassification(10, 4, 5, "INVALID");
        }
    }

    @Nested
    @DisplayName("MC/DC pentru D3: a == b && b == c")
    class Decision3Tests {
        @Test
        void d3AllConditionsTrue() {
            assertClassification(5, 5, 5, "EQUILATERAL");
        }

        @Test
        void d3ConditionC7False() {
            assertClassification(6, 5, 5, "ISOSCELES");
        }

        @Test
        void d3ConditionC8False() {
            assertClassification(5, 5, 6, "ISOSCELES");
        }
    }

    @Nested
    @DisplayName("MC/DC pentru D4: a == b || b == c || a == c")
    class Decision4Tests {
        @Test
        void d4AllConditionsFalse() {
            assertClassification(5, 6, 7, "SCALENE");
        }

        @Test
        void d4ConditionC7True() {
            assertClassification(6, 6, 7, "ISOSCELES");
        }

        @Test
        void d4ConditionC8True() {
            assertClassification(5, 7, 7, "ISOSCELES");
        }

        @Test
        void d4ConditionC9True() {
            assertClassification(5, 6, 5, "ISOSCELES");
        }
    }
}
