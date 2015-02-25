package com.autentia.mixins.dummy.flavours;

public interface EntityFlavour extends Entity {

    Entity getEntity();

    @Override
    default int save() { return getEntity().save(); }

}
