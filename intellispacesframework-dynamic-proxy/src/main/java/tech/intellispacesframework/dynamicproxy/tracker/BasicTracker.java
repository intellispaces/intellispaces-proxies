package tech.intellispacesframework.dynamicproxy.tracker;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class BasicTracker implements Tracker {
  private final List<Method> invokedMethods = new ArrayList<>();

  @Override
  public List<Method> getInvokedMethods() {
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
