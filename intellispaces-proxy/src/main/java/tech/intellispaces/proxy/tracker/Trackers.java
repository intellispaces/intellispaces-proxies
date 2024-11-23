package tech.intellispaces.proxy.tracker;

/**
 * Tracker provider.
 */
public interface Trackers {

  static Tracker get() {
    return new TrackerImpl();
  }
}
