package tech.intellispaces.proxies.factory;

import tech.intellispaces.proxies.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> getProxyClass(ProxyContract<T> contract);

  <T> Class<T> getTrackedClass(Class<T> aClass);
}
