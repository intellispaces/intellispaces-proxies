package tech.intellispaces.dynamicproxy.factory;

import tech.intellispaces.dynamicproxy.proxy.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> getProxyClass(ProxyContract<T> contract);

  <T> Class<T> getTrackedClass(Class<T> aClass);
}
