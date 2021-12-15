package com.my.visitor.model;

import com.my.visitor.visitors.Visitor;

public interface Shape {

    void move(int x, int y);

    void draw();

    String accept(Visitor visitor);
}
