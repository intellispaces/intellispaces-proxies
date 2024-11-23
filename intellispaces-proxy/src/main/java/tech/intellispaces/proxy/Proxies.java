package tech.intellispaces.proxy;

import tech.intellispaces.proxy.contract.ProxyContract;
import tech.intellispaces.proxy.factory.DynamicProxyFactories;

public interface Proxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
