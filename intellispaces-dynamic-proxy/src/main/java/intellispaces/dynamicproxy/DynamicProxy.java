package intellispaces.dynamicproxy;

import intellispaces.dynamicproxy.factory.DynamicProxyFactories;

public interface DynamicProxy {

  static <T> Class<T> createTrackerClass(Class<T> aClass) {
    return DynamicProxyFactories.get().createTrackerClass(aClass);
  }
}
