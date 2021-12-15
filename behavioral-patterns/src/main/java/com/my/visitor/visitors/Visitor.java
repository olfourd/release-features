package com.my.visitor.visitors;

import com.my.visitor.model.Circle;
import com.my.visitor.model.Dot;
import com.my.visitor.model.Rectangle;

public interface Visitor {

    String visitDot(Dot dot);

    String visitCircle(Circle circle);

    String visitRectangle(Rectangle rectangle);
}
