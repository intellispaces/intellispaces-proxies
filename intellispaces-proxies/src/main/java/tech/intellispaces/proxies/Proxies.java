package tech.intellispaces.proxies;

import tech.intellispaces.proxies.contract.ProxyContract;
import tech.intellispaces.proxies.factory.DynamicProxyFactories;

public interface Proxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
