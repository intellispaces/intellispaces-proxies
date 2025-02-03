package tech.intellispaces.commons.proxy.factory;

import tech.intellispaces.commons.proxy.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> getProxyClass(ProxyContract<T> contract);

  <T> Class<T> getTrackedClass(Class<T> aClass);
}
