package intellispaces.proxies.tracker;

public interface TrackerBuilder {

  static Tracker build() {
    return new TrackerImpl();
  }
}
