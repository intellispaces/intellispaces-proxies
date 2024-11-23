package tech.intellispaces.proxy.factory;

import tech.intellispaces.entity.exception.UnexpectedExceptions;

import java.util.ServiceLoader;

/**
 * Proxy factory provider.
 */
public class DynamicProxyFactories {

  /**
   * Returns default proxy factory instance.
   */
  public static DynamicProxyFactory get() {
    if (proxyFactory == null) {
      ServiceLoader<DynamicProxyFactory> serviceLoader = ServiceLoader.load(DynamicProxyFactory.class);
      proxyFactory = serviceLoader.findFirst().orElseThrow(() -> UnexpectedExceptions.withMessage(
          "The implementation of the factory {0} is not provided", DynamicProxyFactory.class.getCanonicalName()));
    }
    return proxyFactory;
  }

  private static DynamicProxyFactory proxyFactory;

  private DynamicProxyFactories() {}
}
