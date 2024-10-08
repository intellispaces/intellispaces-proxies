package intellispaces.common.dynamicproxy.tracker;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Tests for {@link TrackerBuilder} class.
 */
public class TrackerBuilderTest {

  @Test
  public void test() {
    Tracker tracker = TrackerBuilder.build();
    assertThat(tracker.getInvokedMethods()).isEmpty();

    Method method1 = mock(Method.class);
    Method method2 = mock(Method.class);
    tracker.addInvokedMethod(method1);
    tracker.addInvokedMethod(method2);
    assertThat(tracker.getInvokedMethods()).containsExactly(method1, method2);

    tracker.reset();
    assertThat(tracker.getInvokedMethods()).isEmpty();
  }
}
