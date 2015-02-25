package com.autentia.mixins.dummy.flavours;

public class MixtureInspectorDelegate implements MixtureInspector {

    private final Object original;

    public MixtureInspectorDelegate(Object original) {
        this.original = original;
    }

    @Override
    public boolean isOriginal(Object expectedOriginalPerson) {
        return original == expectedOriginalPerson;
    }

}
