package intellispaces.dynamicproxy;

import intellispaces.dynamicproxy.factory.ProxyFactories;

public interface Proxies {

  static <T> Class<T> createWatcherClass(Class<T> aClass) {
    return ProxyFactories.get().createWatcherClass(aClass);
  }
}
