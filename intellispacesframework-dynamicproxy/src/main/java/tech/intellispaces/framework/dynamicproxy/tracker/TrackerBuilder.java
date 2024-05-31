package tech.intellispaces.framework.dynamicproxy.tracker;

public interface TrackerBuilder {

  static Tracker build() {
    return new TrackerImpl();
  }
}
