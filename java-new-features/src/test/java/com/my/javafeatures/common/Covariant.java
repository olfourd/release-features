package com.my.javafeatures.common;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Covariant {

    @Test
    void some() {
        ArrayList<Fruit> fruitList = new ArrayList<>();
        ArrayList<Citrus> citrusList = new ArrayList<>();
        ArrayList<Orange> orangeList = new ArrayList<>();

//        инвариантность — отсутствие наследования между производными типами.
//        invariant(fruitList); нельзя передать
        invariant(citrusList);
//        несмотря на то что нет проблем добавления наследника в список из-за полиморфизма
//        citrusList.add(new Orange());
//        метод не скомпилируется, т.к. передаваемый тип инвариантен
//        invariant(orangeList);

//      ковариантность — сохранение иерархии наследования родительских типов в дочерних типах в том же порядке.
//        получим ошибку компиляции, т.к. передаваемый тип является ковариантным и не сохраняет информацию о родительском классе
//        covariant(fruitList);
        covariant(citrusList);
//        все в порядке, т.к. передаваемый тип является дочерним и инфа по нему передается
        covariant(orangeList);

//      контрвариантность — обращение иерархии родительских типов на противоположную в дочерних типах.
        contrvariant(fruitList);
        contrvariant(citrusList);
//        contrvariant(orangeList);

//      массивы ковариантны. в Java 1.0 не подумали об этом :)
        Object[] objects = new String[10];
        objects[0] = "some";
        objects[1] = 1;
        objects[2] = new Fruit();

//        листы не ковариантны. т.к. в Java 1.2 уже поняли что это может быть проблемой и запретили на уровне компилятора
//        List<Object> objectList = new ArrayList<String>();
//        objectList.add("asda");
//        objectList.add(1L);
//        objectList.add(new Fruit());
    }

    private void invariant(List<Citrus> citruses) {

    }

    private void covariant(List<? extends Citrus> oranges) {
        Fruit fruit = oranges.get(0);
        Citrus citrus = oranges.get(0);
//        не пожем прочитать потомка, т.к. хз (?) какой потомок конкретно
//        Orange orange = oranges.get(0);

//        ничего добавить нельзя
//        oranges.add(new Citrus());
    }

    private void contrvariant(List<? super Citrus> oranges) {
//        читать не можем. просто Object
//        Citrus citrus = oranges.get(0);
//        Orange orange = oranges.get(0);

//        хз какой потомок конкретно
//        oranges.add(new Fruit());
        oranges.add(new Citrus());
        oranges.add(new Orange());
    }

    private class Fruit {

    }

    private class Citrus extends Fruit {

    }

    private class Orange extends Citrus {

    }

    <T> void some(Class<T> clazz) {
        Class<? extends Class> aClass = clazz.getClass();
    }
}
