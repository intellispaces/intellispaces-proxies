package intellispaces.common.dynamicproxy.tracker;

/**
 * Tracker provider.
 */
public interface Trackers {

  static Tracker get() {
    return new TrackerImpl();
  }
}
