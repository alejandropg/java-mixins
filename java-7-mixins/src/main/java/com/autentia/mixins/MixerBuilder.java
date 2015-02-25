package com.autentia.mixins;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds a new {@link Mixer} with a concrete mixins metadata.
 * If the mixins metadata doesn't change, you only needs to instantiate this class one time, and needs to build the Mixer just one time.
 * So the objects of this class and the {@link Mixer} are easily cacheables.
 */
public class MixerBuilder {

    private final ProxyFactory proxyFactory = new ProxyFactory();
    private final List<Mixin> mixins = new ArrayList<>();

    public MixerBuilder(Class<?> interfaceToReturn) {
        proxyFactory.implement(interfaceToReturn);
    }

    public MixerBuilder include(Mixin mixin) {
        proxyFactory.implement(mixin.getInterfaceType());
        mixins.add(mixin);
        return this;
    }

    public Mixer build() {
        proxyFactory.prepareProxyClass();
        return new Mixer(proxyFactory, mixins);
    }

}
