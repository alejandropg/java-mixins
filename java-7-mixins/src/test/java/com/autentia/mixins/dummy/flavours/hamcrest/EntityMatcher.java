package com.autentia.mixins.dummy.flavours.hamcrest;

import com.autentia.mixins.dummy.flavours.Entity;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class EntityMatcher extends BaseMatcher<Entity> {

    public static EntityMatcher isEntity() {
        return new EntityMatcher();
    }

    @Override
    public boolean matches(Object item) {
        final Entity actualEntity = (Entity) item;

        actualEntity.save();
        actualEntity.save();
        final int saveCallsCount = actualEntity.save();

        return saveCallsCount == 3;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue("an Entity saved only 3 times");
    }
}
