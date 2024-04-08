package intellispaces.dynamicproxy.model;

import java.lang.reflect.Method;
import java.util.List;

public interface Watcher {

  void reset();

  List<Method> invokedMethods();
}

