package intellispaces.dynamicproxy.factory;

import intellispaces.common.base.exception.UnexpectedViolationException;

import java.util.ServiceLoader;

public class DynamicProxyFactories {

  /**
   * Returns default proxy factory instance.
   */
  public static DynamicProxyFactory get() {
    if (proxyFactory == null) {
      ServiceLoader<DynamicProxyFactory> serviceLoader = ServiceLoader.load(DynamicProxyFactory.class);
      proxyFactory = serviceLoader.findFirst().orElseThrow(() -> UnexpectedViolationException.withMessage(
          "The implementation of the factory {0} is not provided", DynamicProxyFactory.class.getCanonicalName()));
    }
    return proxyFactory;
  }

  private static DynamicProxyFactory proxyFactory;

  private DynamicProxyFactories() {}
}
