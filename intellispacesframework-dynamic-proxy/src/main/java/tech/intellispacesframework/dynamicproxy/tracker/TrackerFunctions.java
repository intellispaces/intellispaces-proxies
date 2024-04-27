package tech.intellispacesframework.dynamicproxy.tracker;

import tech.intellispacesframework.commons.exception.UnexpectedViolationException;

import java.lang.reflect.Method;
import java.util.List;

public interface TrackerFunctions {

  static void resetTracker(Object tracker) {
    if (!Tracker.class.isAssignableFrom(tracker.getClass())) {
      throw UnexpectedViolationException.withMessage("Object of class {} is not tracker", tracker.getClass().getCanonicalName());
    }
    ((Tracker) tracker).reset();
  }

  static List<Method> getInvokedMethods(Object tracker) {
    if (!Tracker.class.isAssignableFrom(tracker.getClass())) {
      throw UnexpectedViolationException.withMessage("Object of class {} is not tracker", tracker.getClass().getCanonicalName());
    }
    return ((Tracker) tracker).getInvokedMethods();
  }
}
