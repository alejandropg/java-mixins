package com.autentia.mixins.dummy.flavours;

/**
 * This implementations is a simple spy to check behaviour in the tests.
 */
public class EntityDelegate implements Entity {

    private int saveCallsCount = 0;

    public EntityDelegate(Object original) {
        // Ignore the original object, we don't care.
    }

    @Override
    public int save() {
        saveCallsCount++;
        return saveCallsCount;
    }

}
