package com.my.visitor.visitors;

import com.my.visitor.model.Circle;
import com.my.visitor.model.Dot;
import com.my.visitor.model.Rectangle;
import com.my.visitor.model.Shape;

public class XMLExportVisitor implements Visitor {

    public String export(Shape... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>" + "\n");
        for (Shape shape : args) {
            sb.append(shape.accept(this)).append("\n");
        }
        return sb.toString();
    }

    public String visitDot(Dot d) {
        return String.format("""
                   <dot>
                        <id>%s</id>
                        <x>%s</x>
                        <y>%s</y>
                   </dot>
                   """, d.getId(), d.getX(), d.getY());
    }

    public String visitCircle(Circle c) {
        return "<circle>" + "\n" +
                "    <id>" + c.getId() + "</id>" + "\n" +
                "    <x>" + c.getX() + "</x>" + "\n" +
                "    <y>" + c.getY() + "</y>" + "\n" +
                "    <radius>" + c.getRadius() + "</radius>" + "\n" +
                "</circle>";
    }

    public String visitRectangle(Rectangle r) {
        return "<rectangle>" + "\n" +
                "    <id>" + r.id() + "</id>" + "\n" +
                "    <x>" + r.x() + "</x>" + "\n" +
                "    <y>" + r.y() + "</y>" + "\n" +
                "    <width>" + r.width() + "</width>" + "\n" +
                "    <height>" + r.height() + "</height>" + "\n" +
                "</rectangle>";
    }
}
