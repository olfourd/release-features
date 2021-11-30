package com.my.javafeatures.common;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.jupiter.api.Test;

public class OopTest {

    @Test
    void initialBlockTest() {
        new Programmer(1L, "name");
    }

    @Test
    void innerClassTest() {
        House house = new House();
        assertThat(house.getAddress(), is(House.DEFAULT_ADDRESS));

        String city = "Minsk";
        String street = "Kirova";
        String number = "2";
        house.new AddressDefiner(city, street, number)
                .defineAddress();

        assertThat(house.getAddress(), not(House.DEFAULT_ADDRESS));
        assertThat(house.getAddress(), containsString(city));
        assertThat(house.getAddress(), containsString(street));
        assertThat(house.getAddress(), containsString(number));
        System.out.println(house.getAddress());
    }

    @Test
    void staticNestedClassTest() {
        House house = new House();
        assertThat(house.getAddress(), is(House.DEFAULT_ADDRESS));

        String city = "Minsk";
        String street = "Kirova";
        String number = "2";
        String address = House.AddressDefinerStatic.defineAddress(city, street, number);

        house.new AddressDefiner().forceChangeAddress(address);
        assertThat(house.getAddress(), not(House.DEFAULT_ADDRESS));
        assertThat(house.getAddress(), containsString(city));
        assertThat(house.getAddress(), containsString(street));
        assertThat(house.getAddress(), containsString(number));
        System.out.println(house.getAddress());
    }

    @Test
    void methodLocalInnerClass() {
        House house = new House();
        String city = "Minsk";
        String street = "Kirova";
        String number = "2";
        house.new AddressDefiner(city, street, number).defineAddress();

        assertThat(house.isValidAddress(), is(Boolean.TRUE));
    }
}
