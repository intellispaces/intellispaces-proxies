package tech.intellispaces.proxies.tracker;

/**
 * Tracker provider.
 */
public interface Trackers {

  static Tracker get() {
    return new TrackerImpl();
  }
}
