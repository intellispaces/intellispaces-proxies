package intellispaces.dynamicproxy.sample;

import intellispaces.dynamicproxy.tracker.Tracker;

import java.lang.reflect.Method;

public class InterfaceSampleTracked implements InterfaceSample {
  private final Tracker tracker;

  public InterfaceSampleTracked(Tracker tracker) {
    this.tracker = tracker;
  }

  @Override
  public int method1a() {
    tracker.addInvokedMethod(getMethod("method1a"));
    return 0;
  }

  @Override
  public int method1b() {
    tracker.addInvokedMethod(getMethod("method1b"));
    return 0;
  }

  @Override
  public int method2a(int arg) {
    tracker.addInvokedMethod(getMethod("method2a", int.class));
    return 0;
  }

  @Override
  public int method2b(int arg) {
    tracker.addInvokedMethod(getMethod("method2b", int.class));
    return 0;
  }

  public Tracker getTracker() {
    return tracker;
  }

  private Method getMethod(String name, Class<?>... parameterTypes) {
    try {
      return InterfaceSample.class.getDeclaredMethod(name, parameterTypes);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
