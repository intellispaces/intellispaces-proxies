package tech.intellispaces.dynamicproxy;

import tech.intellispaces.dynamicproxy.proxy.contract.ProxyContract;
import tech.intellispaces.dynamicproxy.factory.DynamicProxyFactories;

public interface DynamicProxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
