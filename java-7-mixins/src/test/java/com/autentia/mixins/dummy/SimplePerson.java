package com.autentia.mixins.dummy;

public class SimplePerson implements Person {

    private final String name;
    private final String surname;

    public SimplePerson(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '{' +
                "name='" + name + "', surname='" + surname + "'}";
    }
}
