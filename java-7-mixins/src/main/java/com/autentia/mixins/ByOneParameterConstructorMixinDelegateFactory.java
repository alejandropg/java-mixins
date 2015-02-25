package com.autentia.mixins;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory that creates new delegate instances using a constructor with just one parameter of type Object. Passes the
 * original object as the argument of the constructor.
 *
 * @param <T> type of the mixin's delegate.
 */
public class ByOneParameterConstructorMixinDelegateFactory<T> implements MixinDelegateFactory<T> {

    private final Constructor<?> delegateConstructor;

    public ByOneParameterConstructorMixinDelegateFactory(Class<?> delegateClass) {
        try {
            delegateConstructor = delegateClass.getConstructor(Object.class);

        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class " + delegateClass.getName() + " doesn't have a constructor with just one argument of type Object", e);
        }
    }

    @Override
    public T createDelegateToMixWith(Object original) {
        try {
            final Object newInstance = delegateConstructor.newInstance(original);

            @SuppressWarnings("unchecked")
            final T newDelegate = (T) newInstance;

            return newDelegate;

        } catch (InvocationTargetException | IllegalAccessException | InstantiationException | ClassCastException e) {
            throw new RuntimeException("Cannot create new instance of " + delegateConstructor.getDeclaringClass().getName(), e);
        }
    }
}
