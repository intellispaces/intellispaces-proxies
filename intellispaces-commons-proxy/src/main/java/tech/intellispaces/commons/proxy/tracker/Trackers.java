package tech.intellispaces.commons.proxy.tracker;

/**
 * Tracker provider.
 */
public interface Trackers {

  static Tracker get() {
    return new TrackerImpl();
  }
}
