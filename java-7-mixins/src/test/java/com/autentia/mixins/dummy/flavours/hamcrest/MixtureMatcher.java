package com.autentia.mixins.dummy.flavours.hamcrest;

import com.autentia.mixins.dummy.SimplePerson;
import com.autentia.mixins.dummy.flavours.MixtureInspector;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class MixtureMatcher extends BaseMatcher<MixtureInspector> {

    public static MixtureMatcher hasOriginal(SimplePerson expectedOriginal) {
        return new MixtureMatcher(expectedOriginal);
    }

    private final Object expectedOriginal;

    public MixtureMatcher(Object expectedOriginal) {
        this.expectedOriginal = expectedOriginal;
    }

    @Override
    public boolean matches(Object item) {
        final MixtureInspector actualMixtureInspector = (MixtureInspector) item;

        return actualMixtureInspector.isOriginal(expectedOriginal);
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue("mixture with original ").appendValue(expectedOriginal);
    }
}
