package intellispaces.dynamicproxy.factory;

import intellispaces.dynamicproxy.contract.ProxyContract;

public interface DynamicProxyFactory {

  <T> Class<T> getProxyClass(ProxyContract<T> contract);

  <T> Class<T> getTrackedClass(Class<T> aClass);
}
