package tech.intellispaces.framework.dynamicproxy.factory;

import tech.intellispaces.framework.dynamicproxy.proxy.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> createProxyClass(ProxyContract<T> contract);

  <T> Class<T> createTrackedClass(Class<T> aClass);
}
