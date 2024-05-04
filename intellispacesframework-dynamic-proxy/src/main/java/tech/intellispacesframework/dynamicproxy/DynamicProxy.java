package tech.intellispacesframework.dynamicproxy;

import tech.intellispacesframework.dynamicproxy.factory.DynamicProxyFactories;
import tech.intellispacesframework.dynamicproxy.proxy.contract.ProxyContract;

public interface DynamicProxy {

  static <T> Class<T> createProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().createProxyClass(contract);
  }

  static <T> Class<T> createTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().createTrackedClass(aClass);
  }
}
