package com.autentia.mixins.dummy.flavours.hamcrest;

import com.autentia.mixins.dummy.SimplePerson;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class PersonMatcher extends BaseMatcher<SimplePerson> {

    public static PersonMatcher isPerson(SimplePerson expectedPerson) {
        return new PersonMatcher(expectedPerson);
    }

    private final SimplePerson expectedPerson;

    public PersonMatcher(SimplePerson expectedPerson) {
        this.expectedPerson = expectedPerson;
    }

    @Override
    public boolean matches(Object item) {
        final SimplePerson actualPerson = (SimplePerson) item;

        return actualPerson.getName().equals(expectedPerson.getName())
                && actualPerson.getSurname().equals(expectedPerson.getSurname());
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(expectedPerson);
    }
}
