package tech.intellispaces.framework.dynamicproxy.tracker;

import tech.intellispaces.framework.commons.exception.UnexpectedViolationException;
import tech.intellispaces.framework.dynamicproxy.DynamicProxy;

import java.util.HashMap;
import java.util.Map;

public class TrackerFunctions {

  @SuppressWarnings("unchecked")
  public static <T> Class<T> getOrCreateTrackedClass(Class<T> aClass) {
    return (Class<T>) TRACKED_CLASSES_CACHE.computeIfAbsent(aClass, cls -> createTrackedClass(aClass));
  }

  private static <T> Class<T> createTrackedClass(Class<T> aClass) {
    return DynamicProxy.createTrackedClass(aClass);
  }

  public static <S> S createTrackedObject(Class<S> aClass, Tracker tracker) {
    try {
      return getOrCreateTrackedClass(aClass).getConstructor(Tracker.class).newInstance(tracker);
    } catch (Exception e) {
      throw UnexpectedViolationException.withCauseAndMessage(e, "Failed to create tracked object of the class {}", aClass.getCanonicalName());
    }
  }

  private static final Map<Class<?>, Object> TRACKED_CLASSES_CACHE = new HashMap<>();

  private TrackerFunctions() {}
}
