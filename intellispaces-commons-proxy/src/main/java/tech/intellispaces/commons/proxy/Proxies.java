package tech.intellispaces.commons.proxy;

import tech.intellispaces.commons.proxy.contract.ProxyContract;
import tech.intellispaces.commons.proxy.factory.DynamicProxyFactories;

public interface Proxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
