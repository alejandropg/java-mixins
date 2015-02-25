package com.autentia.mixins;

import com.autentia.mixins.dummy.Person;
import com.autentia.mixins.dummy.SimplePerson;
import com.autentia.mixins.dummy.flavours.Entity;
import com.autentia.mixins.dummy.flavours.EntityDelegate;
import com.autentia.mixins.dummy.flavours.MixtureInspector;
import com.autentia.mixins.dummy.flavours.MixtureInspectorDelegate;
import com.autentia.mixins.dummy.flavours.PoweredPerson;
import org.junit.Test;

import static com.autentia.mixins.dummy.flavours.hamcrest.EntityMatcher.isEntity;
import static com.autentia.mixins.dummy.flavours.hamcrest.MixtureMatcher.hasOriginal;
import static com.autentia.mixins.dummy.flavours.hamcrest.PersonMatcher.isPerson;
import static org.junit.Assert.assertThat;

public class MixinTest {

    @Test
    public void buildAndUseCastingToAccessItsMethods() throws Exception {

        final SimplePerson originalPerson = new SimplePerson("John", "Doe");

        final Person person = new MixerBuilder(Person.class)
                .include(new Mixin(Entity.class, EntityDelegate.class))
                .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
                .build()
                .mixWith(originalPerson);

        assertThat(person, isPerson(originalPerson));
        assertThat((Entity) person, isEntity());
        assertThat((MixtureInspector) person, hasOriginal(originalPerson));
    }

    @Test
    public void buildAndNotUseCastingToAccessItsMethods() throws Exception {

        final SimplePerson originalPerson = new SimplePerson("John", "Doe");

        final PoweredPerson poweredPerson = new MixerBuilder(PoweredPerson.class)
                .include(new Mixin(Entity.class, EntityDelegate.class))
                .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
                .build()
                .mixWith(originalPerson);

        assertPoweredPerson(poweredPerson, originalPerson);
    }

    private void assertPoweredPerson(PoweredPerson actualPoweredPerson, SimplePerson expectedOriginalPerson) {
        assertThat(actualPoweredPerson, isPerson(expectedOriginalPerson));
        assertThat(actualPoweredPerson, isEntity());
        assertThat(actualPoweredPerson, hasOriginal(expectedOriginalPerson));
    }

    @Test
    public void buildSeveralMixturesWithSameMixinsMetadata() throws Exception {

        // Builds the Factory just one time
        final Mixer mixer = new MixerBuilder(PoweredPerson.class)
                .include(new Mixin(Entity.class, EntityDelegate.class))
                .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
                .build();

        // Builds all the mixtures that you want!
        final SimplePerson originalPerson1 = new SimplePerson("John", "Doe");
        final PoweredPerson poweredPerson1 = mixer.mixWith(originalPerson1);

        final SimplePerson originalPerson2 = new SimplePerson("Jane", "Doe");
        final PoweredPerson poweredPerson2 = mixer.mixWith(originalPerson2);

        final SimplePerson originalPerson3 = new SimplePerson("Joe", "Public");
        final PoweredPerson poweredPerson3 = mixer.mixWith(originalPerson3);

        assertPoweredPerson(poweredPerson1, originalPerson1);
        assertPoweredPerson(poweredPerson2, originalPerson2);
        assertPoweredPerson(poweredPerson3, originalPerson3);
    }

    @Test
    public void buildUsingOurOwnDelegateFactory() throws Exception {

        final SimplePerson originalPerson = new SimplePerson("John", "Doe");

        final PoweredPerson poweredPerson = new MixerBuilder(PoweredPerson.class)
                .include(new Mixin(Entity.class, EntityDelegate.class))
                .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class,
                        new MixinDelegateFactory<MixtureInspectorDelegate>() {
                            @Override
                            public MixtureInspectorDelegate createDelegateToMixWith(Object original) {
                                return new MixtureInspectorDelegate(original);
                            }
                        }))
                .build()
                .mixWith(originalPerson);

        assertPoweredPerson(poweredPerson, originalPerson);
    }
}
