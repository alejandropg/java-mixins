package com.autentia.mixins.dummy;

public class SimplePerson {

    private final String name;
    private final String surname;

    public SimplePerson(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '{' +
                "name='" + name + "', surname='" + surname + "'}";
    }
}
