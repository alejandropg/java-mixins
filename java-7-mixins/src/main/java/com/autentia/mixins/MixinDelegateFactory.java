package com.autentia.mixins;

/**
 * You can implement this interface to take control over how new delegate instances are created.
 *
 * @param <T> type of the mixin's delegate.
 */
public interface MixinDelegateFactory<T> {

    /**
     * This method is called when a new delegate instance is needed.
     *
     * @param original the object that will be garnished by the delegate instance returned by this method.
     * @return the new delegate instance to mixWith the original object.
     */
    T createDelegateToMixWith(Object original);

}
