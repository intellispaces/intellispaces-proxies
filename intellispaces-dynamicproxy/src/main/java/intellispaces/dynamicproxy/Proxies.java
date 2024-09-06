package intellispaces.dynamicproxy;

import intellispaces.dynamicproxy.contract.ProxyContract;
import intellispaces.dynamicproxy.factory.DynamicProxyFactories;

public interface Proxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
