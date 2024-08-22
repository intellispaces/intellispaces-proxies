package intellispaces.proxies.contract;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Contract to create proxy class.
 *
 * @param <T> proxy type.
 */
public interface ProxyContract<T> {

  /**
   * Proxy class name.
   */
  String className();

  /**
   * Proxy type (class or interface).
   */
  Class<T> type();

  /**
   * Additional interfaces implemented by proxy.
   */
  List<Class<?>> additionalInterfaces();

  /**
   * Proxy method handles.
   */
  Map<Method, MethodHandler> methodHandlers();

  /**
   * Handler of abstract methods.
   */
  Optional<CommonMethodHandler> abstractMethodHandler();
}
