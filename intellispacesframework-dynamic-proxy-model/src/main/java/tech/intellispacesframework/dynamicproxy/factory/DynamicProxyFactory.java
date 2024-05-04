package tech.intellispacesframework.dynamicproxy.factory;

import tech.intellispacesframework.dynamicproxy.proxy.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> createProxyClass(ProxyContract<T> contract);

  <T> Class<T> createTrackedClass(Class<T> aClass);
}
