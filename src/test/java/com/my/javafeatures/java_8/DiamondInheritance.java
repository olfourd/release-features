package com.my.javafeatures.java_8;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class DiamondInheritance {

    @Test
    void diamondInheritance() {
        D d = new D();
        assertThat(d.display(), is("B C"));
    }

    interface A {
        String display();
    }

    interface B extends A {
        @Override
        default String display() {
            return B.class.getSimpleName();
        }
    }

    interface C extends A {
        @Override
        default String display() {
            return C.class.getSimpleName();
        }
    }

    class D implements B, C {
        @Override
        public String display() {
            String displayB = B.super.display();
            String displayC = C.super.display();
            return String.format("%s %s", displayB, displayC);
        }
    }
}
