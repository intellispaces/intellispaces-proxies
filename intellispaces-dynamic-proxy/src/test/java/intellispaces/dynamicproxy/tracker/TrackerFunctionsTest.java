package intellispaces.dynamicproxy.tracker;

import intellispaces.commons.exception.UnexpectedViolationException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link TrackerFunctions} class.
 */
public class TrackerFunctionsTest {

  @Test
  public void testResetTracker_whenIsTracker() {
    // Given
    Tracker tracker = spy(new BasicTracker());

    // When
    TrackerFunctions.resetTracker(tracker);

    // Then
    verify(tracker).reset();
  }

  @Test
  public void testGetInvokedMethods_whenIsTracker() {
    // Given
    Tracker tracker = spy(new BasicTracker());

    List<Method> methods = new ArrayList<>();
    when(tracker.getInvokedMethods()).thenReturn(methods);

    // When
    List<Method> answer = TrackerFunctions.getInvokedMethods(tracker);

    // Then
    assertThat(answer).isSameAs(methods);
    verify(tracker).getInvokedMethods();
  }

  @Test
  public void testResetTracker_whenIsNotTracker() {
    assertThatThrownBy(
        () -> TrackerFunctions.resetTracker(new Object())
    ).isExactlyInstanceOf(UnexpectedViolationException.class);
  }

  @Test
  public void testGetInvokedMethods_whenIsNotTracker() {
    assertThatThrownBy(
        () -> TrackerFunctions.getInvokedMethods(new Object())
    ).isExactlyInstanceOf(UnexpectedViolationException.class);
  }
}
