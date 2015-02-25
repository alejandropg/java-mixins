package com.autentia.mixins.dummy.flavours;

import com.autentia.mixins.dummy.SimplePerson;

public class PoweredPerson extends SimplePerson implements EntityFlavour, MixtureInspectorFlavour {

    private final Entity entity = new EntityDelegate(this);
    private final MixtureInspector mixtureInspector = new MixtureInspectorDelegate(this);

    public PoweredPerson(String name, String surname) {
        super(name, surname);
    }

    @Override
    public Entity getEntity() {
        return entity;
    }

    @Override
    public MixtureInspector getMixtureInspector() {
        return mixtureInspector;
    }
}
