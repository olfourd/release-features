package com.my.javafeatures.java_14;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.my.javafeatures.java_14.sealed_class.AmazingRectangle;
import com.my.javafeatures.java_14.sealed_class.Circle;
import com.my.javafeatures.java_14.sealed_class.Rectangle;
import com.my.javafeatures.java_14.sealed_class.Shape;
import com.my.javafeatures.java_14.sealed_class.Square;
import org.junit.jupiter.api.Test;

public class SyntaxFeaturesTest {

    @Test
    void switchTest() {
        assertThat(numToStringValue(1), is("one"));
        assertThat(numToStringValue(2), is("two"));
        assertThat(numToStringValue(3), is("three"));
        assertThat(numToStringValue(4), is("many"));
    }

    String numToStringValue(int number) {
        return switch (number) {
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            default -> {
                System.out.printf("can't find string value for value {}%n", number);
                yield "many";
            }
        };
    }

    @Test
    void recordTest() {
        Dto recordDto = new Dto(1, numToStringValue(1));
        System.out.println(recordDto);
        assertThat(recordDto.id(), is(1));
        assertThat(recordDto.name(), is("one"));
        System.out.println(recordDto.testPublicMethod());
    }

    @Test
    void stringBlock() {
        String block = """
                line1 \
                line2\s
                line3
                """;

        assertThat(block, is("line1 line2 \nline3\n"));
    }

    public record Dto(int id, String name) {
        public String testPublicMethod() {
            return String.format("Hello, my name is %s, id is %s", this.name, this.id);
        }
    }

    @Test
    void sealedClass() {
        Shape rectangle = new Rectangle();
        assertThat(rectangle.getClassName(), is(Rectangle.class.getName()));
        Shape circle = new Circle();
        assertThat(circle.getClassName(), is(Circle.class.getName()));
        Shape square = new Square();
        assertThat(square.getClassName(), is(Square.class.getName()));
        Shape amazingRectangle = new AmazingRectangle();
        assertThat(amazingRectangle.getClassName(), is(AmazingRectangle.class.getName()));
        Rectangle aRectangle = new AmazingRectangle();
        assertThat(aRectangle.getClassName(), is(AmazingRectangle.class.getName()));
    }
}
