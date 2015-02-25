package com.autentia.mixins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Factory to create new mixture instances.
 * The objects of this class could be reused to create several mixtures of the same type. So this class are easily cacheable.
 */
public class Mixer {

    private final ProxyFactory proxyClass;
    private final List<Mixin> mixins;

    /**
     * Package level, to just to be used from {@link MixerBuilder}
     *
     * @param proxyClass to create new proxy instances.
     * @param mixins     to be included into the original object.
     */
    Mixer(ProxyFactory proxyClass, List<Mixin> mixins) {
        this.proxyClass = proxyClass;
        this.mixins = mixins;
    }

    /**
     * Mix/blend/garnish/season/decorate the original object with the methods of all mixins, returning a new mixture instance.
     *
     * @param original object to mix with.
     * @param <T>      the type of the mixture that will be returned.
     * @return new mixture = original + mixins.
     */
    public <T> T mixWith(Object original) {
        final Map<Class<?>, Object> delegatesByInterfaceType = createDelegatesByInterfaceType(original);
        final Object proxy = createProxy(original, delegatesByInterfaceType);

        @SuppressWarnings("unchecked")
        final T mixin = (T) proxy;

        return mixin;
    }

    /**
     * Prepare a map where the key is the interface type, and the value is the instance of the delegate that implements
     * that interface.
     *
     * @param original object where methods will be "added".
     * @return map.
     */
    private Map<Class<?>, Object> createDelegatesByInterfaceType(Object original) {
        final Map<Class<?>, Object> delegatesByInterfaceType = new HashMap<>();

        for (Mixin mixin : mixins) {
            final Object mixinDelegate = mixin.createDelegateToMixWith(original);
            delegatesByInterfaceType.put(mixin.getInterfaceType(), mixinDelegate);
        }

        return delegatesByInterfaceType;
    }

    /**
     * Create new proxy instance that wraps the original object and all the delegates. Depending on the method that
     * is invoked, the proxy will call methods on the delegate instances or the original object.
     *
     * @param original                 object that is mixed with.
     * @param delegatesByInterfaceType map with the instances of the delegates.
     * @return new proxy instance.
     */
    private Object createProxy(final Object original, final Map<Class<?>, Object> delegatesByInterfaceType) {
        return proxyClass.createProxy(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object objectToCall = delegatesByInterfaceType.get(method.getDeclaringClass());
                if (objectToCall == null) {
                    objectToCall = original;
                }

                return method.invoke(objectToCall, args);
            }
        });

    }
}
