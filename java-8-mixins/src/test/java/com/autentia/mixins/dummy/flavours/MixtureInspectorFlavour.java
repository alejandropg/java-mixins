package com.autentia.mixins.dummy.flavours;

public interface MixtureInspectorFlavour extends MixtureInspector {

    MixtureInspector getMixtureInspector();

    @Override
    default boolean isOriginal(Object expectedOriginalPerson) { return getMixtureInspector().isOriginal(expectedOriginalPerson); }

}
