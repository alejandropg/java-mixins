
# Java Mixins

This is a very simple library to show how to implement _mixins_ in Java.

The next code you can see how to use the library. You can find more examples in the test folder.

```java
final Person originalPerson = new SimplePerson("John", "Doe");

final Person person = new MixerBuilder(Person.class)
        .include(new Mixin(Entity.class, EntityDelegate.class))
        .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
        .build()
        .mixWith(originalPerson);

assertThat(person, isPerson(originalPerson));
assertThat((Entity) person, isEntity());
assertThat((MixtureInspector) person, hasOriginal(originalPerson));
```

Also you can use the library without the need of casting:

```java
final Person originalPerson = new SimplePerson("John", "Doe");

final PoweredPerson poweredPerson = new MixerBuilder(PoweredPerson.class)
        .include(new Mixin(Entity.class, EntityDelegate.class))
        .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
        .build()
        .mixWith(originalPerson);

assertThat(poweredPerson, isPerson(originalPerson));
assertThat(poweredPerson, isEntity());
assertThat(poweredPerson, hasOriginal(originalPerson));
```

Also you can cache the factory:

```java
// Builds the Factory just one time
final Mixer mixer = new MixerBuilder(PoweredPerson.class)
        .include(new Mixin(Entity.class, EntityDelegate.class))
        .include(new Mixin(MixtureInspector.class, MixtureInspectorDelegate.class))
        .build();

// Builds all the mixtures that you want!
final PoweredPerson poweredPerson1 = mixer.mixWith(new SimplePerson("John", "Doe"));
final PoweredPerson poweredPerson2 = mixer.mixWith(new SimplePerson("Jane", "Doe"));
final PoweredPerson poweredPerson3 = mixer.mixWith(new SimplePerson("Joe", "Public"));
```

## DOCS & TUTORIALS

*   [Spanish tutorial in adictosaltrabajo.com](http://www.adictosaltrabajo.com/tutoriales/tutoriales.php?pagina=java-mixins "Spanish tutorial in adictosaltrabajo.com")

