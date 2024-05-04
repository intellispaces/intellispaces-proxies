package tech.intellispacesframework.dynamicproxy.tracker;

public interface TrackerBuilder {

  static Tracker build() {
    return new TrackerImpl();
  }
}
