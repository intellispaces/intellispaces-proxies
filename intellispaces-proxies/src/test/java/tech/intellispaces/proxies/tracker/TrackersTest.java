package tech.intellispaces.proxies.tracker;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link Trackers} class.
 */
public class TrackersTest {

  @Test
  public void test() throws Exception {
    Tracker tracker = Trackers.get();
    assertThat(tracker.getInvokedMethods()).isEmpty();

    Method method1 = String.class.getMethod("isEmpty");
    Method method2 = String.class.getMethod("trim");
    tracker.addInvokedMethod(method1);
    tracker.addInvokedMethod(method2);
    assertThat(tracker.getInvokedMethods()).containsExactly(method1, method2);

    tracker.reset();
    assertThat(tracker.getInvokedMethods()).isEmpty();
  }
}
