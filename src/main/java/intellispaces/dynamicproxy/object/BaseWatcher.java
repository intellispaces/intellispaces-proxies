package intellispaces.dynamicproxy.object;

import intellispaces.dynamicproxy.model.Watcher;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BaseWatcher implements Watcher {
  private final List<Method> invokedMethods = new ArrayList<>();

  @Override
  public List<Method> invokedMethods() {
    return List.copyOf(invokedMethods);
  }

  @Override
  public void reset() {
    invokedMethods.clear();
  }

  public void addInvokedMethod(Method method) {
    this.invokedMethods.add(method);
  }
}
