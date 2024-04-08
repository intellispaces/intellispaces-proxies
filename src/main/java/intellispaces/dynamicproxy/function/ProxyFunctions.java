package intellispaces.dynamicproxy.function;

import intellispaces.commons.exception.UnexpectedViolationException;
import intellispaces.dynamicproxy.model.Watcher;

import java.lang.reflect.Method;
import java.util.List;

public interface ProxyFunctions {

  static void resetWatcher(Object watcher) {
    if (!Watcher.class.isAssignableFrom(watcher.getClass())) {
      throw new UnexpectedViolationException("Object of class {} is not watcher", watcher.getClass().getCanonicalName());
    }
    ((Watcher) watcher).reset();
  }

  static List<Method> invokedMethodsOf(Object watcher) {
    if (!Watcher.class.isAssignableFrom(watcher.getClass())) {
      throw new UnexpectedViolationException("Object of class {} is not watcher", watcher.getClass().getCanonicalName());
    }
    return ((Watcher) watcher).invokedMethods();
  }
}
