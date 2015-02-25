package com.autentia.mixins;

import com.autentia.mixins.dummy.flavours.PoweredPerson;
import org.junit.Test;

import static com.autentia.mixins.dummy.flavours.hamcrest.EntityMatcher.isEntity;
import static com.autentia.mixins.dummy.flavours.hamcrest.MixtureMatcher.hasOriginal;
import static com.autentia.mixins.dummy.flavours.hamcrest.PersonMatcher.isPerson;
import static org.junit.Assert.assertThat;

public class MixinTest {

    @Test
    public void buildAndAccessItsMethods() throws Exception {
        final PoweredPerson poweredPerson = new PoweredPerson("John", "Doe");

        assertPoweredPerson(poweredPerson);
    }

    @Test
    public void buildSeveralMixins() throws Exception {
        final PoweredPerson poweredPerson1 = new PoweredPerson("John", "Doe");
        final PoweredPerson poweredPerson2 = new PoweredPerson("Jane", "Doe");
        final PoweredPerson poweredPerson3 = new PoweredPerson("Joe", "Public");

        assertPoweredPerson(poweredPerson1);
        assertPoweredPerson(poweredPerson2);
        assertPoweredPerson(poweredPerson3);
    }

    private void assertPoweredPerson(PoweredPerson actualPoweredPerson) {
        assertThat(actualPoweredPerson, isPerson(actualPoweredPerson));
        assertThat(actualPoweredPerson, isEntity());
        assertThat(actualPoweredPerson, hasOriginal(actualPoweredPerson));
    }

}
