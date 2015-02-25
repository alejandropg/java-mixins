package com.autentia.mixins;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Helps to create new proxy instances.
 */
class ProxyFactory {

    private final List<Class<?>> interfacesToImplement = new ArrayList<>();
    private Class<?> proxyClass;

    ProxyFactory implement(Class<?> interfaceType) {
        interfacesToImplement.add(interfaceType);
        return this;
    }

    /**
     * Prepare the Proxy Class to implement all added interfaces.
     */
    void prepareProxyClass() {
        proxyClass = Proxy.getProxyClass(
                interfacesToImplement.get(0).getClassLoader(),
                interfacesToImplement.toArray(new Class[interfacesToImplement.size()])
        );
    }

    /**
     * Create new proxy instance.
     *
     * @param invocationHandler to handle methods invocations.
     * @return the new proxy instance.
     */
    Object createProxy(InvocationHandler invocationHandler) {
        try {
            return proxyClass.getConstructor(InvocationHandler.class).newInstance(invocationHandler);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            // This error should never occur, because we are calling the Proxy interface that is well known.
            throw new UnknownError("Cannot create new proxy instance of a Proxy class: " + e.getLocalizedMessage());
        }
    }

}
