package intellispaces.dynamicproxy.factory;

import intellispaces.commons.exception.UnexpectedViolationException;

import java.util.ServiceLoader;

public class ProxyFactories {

  /**
   * Returns default proxy factory instance.
   */
  public static ProxyFactory get() {
    if (proxyFactory == null) {
      ServiceLoader<ProxyFactory> serviceLoader = ServiceLoader.load(ProxyFactory.class);
      proxyFactory = serviceLoader.findFirst().orElseThrow(() -> new UnexpectedViolationException(
          "The implementation of the factory {} is not provided", ProxyFactory.class.getCanonicalName()));
    }
    return proxyFactory;
  }

  private static ProxyFactory proxyFactory;

  private ProxyFactories() {}
}
