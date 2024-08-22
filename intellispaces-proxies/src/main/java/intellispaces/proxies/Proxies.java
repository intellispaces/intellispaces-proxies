package intellispaces.proxies;

import intellispaces.proxies.contract.ProxyContract;
import intellispaces.proxies.factory.DynamicProxyFactories;

public interface Proxies {

  static <T> Class<T> getProxyClass(ProxyContract<T> contract) {
    return DynamicProxyFactories.get().getProxyClass(contract);
  }

  static <T> Class<T> getTrackedClass(Class<T> aClass) {
    return DynamicProxyFactories.get().getTrackedClass(aClass);
  }
}
