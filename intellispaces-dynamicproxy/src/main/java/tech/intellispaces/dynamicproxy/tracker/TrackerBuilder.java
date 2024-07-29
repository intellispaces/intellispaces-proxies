package tech.intellispaces.dynamicproxy.tracker;

public interface TrackerBuilder {

  static Tracker build() {
    return new TrackerImpl();
  }
}
