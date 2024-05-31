package tech.intellispaces.framework.dynamicproxy;

import tech.intellispaces.framework.dynamicproxy.factory.DynamicProxyFactories;
import tech.intellispaces.framework.dynamicproxy.proxy.contract.ProxyContract;

public interface DynamicProxy {

  static <T> Class<T> createProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().createProxyClass(contract);
  }

  static <T> Class<T> createTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().createTrackedClass(aClass);
  }
}
