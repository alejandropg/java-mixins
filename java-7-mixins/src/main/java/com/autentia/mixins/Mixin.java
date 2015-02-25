package com.autentia.mixins;

/**
 * Specifies a new mixin. A mixin is an interface plus a delegate which implements the interface.
 * This class knows how to create new delegate instances.
 */
public class Mixin {

    private final Class<?> interfaceType;
    private final MixinDelegateFactory<?> delegateFactory;

    /**
     * Creates a new instance of a mixin.
     *
     * @param interfaceType of the mixin.
     * @param delegateClass of the mixin. This class should have a constructor with just one parameter of type Object
     *                      (this Object will be the original object to be garnished by the delegate).
     */
    public <T> Mixin(Class<?> interfaceType, Class<T> delegateClass) {
        this(interfaceType, delegateClass, new ByOneParameterConstructorMixinDelegateFactory<T>(delegateClass));
    }

    /**
     * Creates a new instance of a specify mixin.
     *
     * @param interfaceType   of the mixin.
     * @param delegateFactory to create new instances of the delegate.
     */
    public <T> Mixin(Class<?> interfaceType, Class<T> delegateClass, MixinDelegateFactory<T> delegateFactory) {
        assertThatImplements(delegateClass, interfaceType);

        this.interfaceType = interfaceType;
        this.delegateFactory = delegateFactory;
    }

    private void assertThatImplements(Class<?> actualClass, Class<?> expectedInterface) {
        if (!expectedInterface.isAssignableFrom(actualClass)) {
            throw new IllegalArgumentException(actualClass.getName() + " not implements " + expectedInterface.getName());
        }
    }

    Class<?> getInterfaceType() {
        return interfaceType;
    }

    /**
     * Used from {@link Mixer} to create new instance of the delegate.
     *
     * @param original object to be garnished.
     * @return new delegate instance.
     */
    Object createDelegateToMixWith(Object original) {
        return delegateFactory.createDelegateToMixWith(original);
    }
}
