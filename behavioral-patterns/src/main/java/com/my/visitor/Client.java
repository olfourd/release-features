package com.my.visitor;

import com.my.visitor.model.Circle;
import com.my.visitor.model.Dot;
import com.my.visitor.model.Rectangle;
import com.my.visitor.model.Shape;
import com.my.visitor.visitors.XMLExportVisitor;

public class Client {

    public static void main(String[] args) {
        Dot dot = new Dot(1, 10, 55);
        Circle circle = new Circle(2, 23, 15, 10);
        Rectangle rectangle = new Rectangle(3, 10, 17, 20, 30);

        export(dot, circle, rectangle);
    }

    private static void export(Shape... shapes) {
        XMLExportVisitor exportVisitor = new XMLExportVisitor();
        System.out.println(exportVisitor.export(shapes));
    }
}
